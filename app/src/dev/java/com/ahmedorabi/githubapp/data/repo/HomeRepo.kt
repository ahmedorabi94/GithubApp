package com.ahmedorabi.githubapp.data.repo

import com.ahmedorabi.githubapp.data.api.ApiService
import com.ahmedorabi.githubapp.data.api.ResultWrapper
import com.ahmedorabi.githubapp.data.api.safeApiCall
import com.ahmedorabi.githubapp.data.db.RepoDao
import com.ahmedorabi.githubapp.data.model.GithubResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Named

class HomeRepo @Inject constructor(
    @Named("MockApiService") private val apiServiceFake: ApiService, private val repoDao: RepoDao
) {


    private val dispatcher: CoroutineDispatcher = Dispatchers.IO


    suspend fun getReposResponseFlow(
        q: String,
    ): Flow<ResultWrapper<GithubResponse>> {

        return flow {


            val apiResponse = safeApiCall(dispatcher) {
                apiServiceFake.getRepositoriesAsync(q)
            }

            when (apiResponse) {
                is ResultWrapper.Success -> {
                    repoDao.deleteAllRepos()
                    repoDao.insertRepos(apiResponse.value.items)
                    emit(
                        ResultWrapper.Success(
                            GithubResponse(
                                false,
                                repoDao.getAllRepos(q),
                                1
                            )
                        )
                    )

                }
                else -> {
                    emit(apiResponse)

                    emit(
                        ResultWrapper.Success(
                            GithubResponse(
                                false,
                                repoDao.getAllRepos(q),
                                1
                            )
                        )
                    )
                }
            }


        }.flowOn(Dispatchers.IO)
    }


}