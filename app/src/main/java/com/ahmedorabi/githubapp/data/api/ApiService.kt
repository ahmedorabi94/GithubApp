package com.ahmedorabi.githubapp.data.api

import com.ahmedorabi.githubapp.data.model.GithubResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @GET("search/repositories")
    @Headers("Authorization: token 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")
    suspend fun getRepositoriesAsync(
        @Query("q") apiKey: String
    ): GithubResponse

}