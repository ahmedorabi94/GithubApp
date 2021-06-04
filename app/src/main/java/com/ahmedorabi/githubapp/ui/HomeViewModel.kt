package com.ahmedorabi.githubapp.ui

import androidx.lifecycle.*
import com.ahmedorabi.githubapp.data.api.Resource
import com.ahmedorabi.githubapp.data.api.ResultWrapper
import com.ahmedorabi.githubapp.data.model.GithubResponse
import com.ahmedorabi.githubapp.data.repo.HomeRepo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val repo: HomeRepo) : ViewModel() {

    val query = MutableStateFlow("")


    val _items = query
        .debounce(500L)
        .filter { query ->
            return@filter !query.isEmpty()
        }
        .distinctUntilChanged()
        .flatMapLatest { query ->
            getRepositoriesFlow(query)
        }
        .flowOn(Dispatchers.Default)

    val items = _items.asLiveData()


    private fun getRepositoriesFlow(q: String): Flow<Resource<GithubResponse>> {


        return flow {
            repo.getReposResponseFlow(q)
                .onStart {
                    Timber.e("Start")
                    emit(Resource.loading(data = null))
                }.catch { exception ->
                    Timber.e(exception)
                    emit(Resource.error(data = null, message = "Unknown Error"))
                }.onCompletion {
                    Timber.e("onCompletion")
                }.collect { response ->

                    when (response) {
                        is ResultWrapper.Success -> {
                            Timber.e("Success ${response.value}")
                            emit(Resource.success(response.value))
                        }
                        is ResultWrapper.Error -> {
                            val errorResponse = response.error
                            val code = response.code

                            Timber.e("ApiError Code : $code  Message : ${errorResponse?.message}")

                            if (errorResponse != null) {
                                emit(
                                    Resource.error(
                                        data = null,
                                        message = errorResponse.message
                                    )
                                )
                            } else {
                                emit(Resource.error(data = null, message = "Unknown Error"))
                            }
                        }
                        is ResultWrapper.NetworkError -> {
                            emit(Resource.error(data = null, message = "NetworkError ."))
                        }

                    }


                }

        }


    }
}