package com.test.githubuser.core.data.source.remote.network

import com.test.githubuser.core.data.source.remote.response.BaseResponse
import com.test.githubuser.core.data.source.remote.response.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("search/users")
    suspend fun getUser(
        @Query("q") username: String,
        @Query("page") page:Int
    ): BaseResponse<UserResponse>
}