package com.ahmedorabi.githubapp.data.api

import com.ahmedorabi.githubapp.data.model.GithubResponse
import com.ahmedorabi.githubapp.utils.AppConstant
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories")
    @Headers("Authorization: token ${AppConstant.TOKEN}")
    suspend fun getRepositoriesAsync(
        @Query("q") apiKey: String
    ): GithubResponse

}