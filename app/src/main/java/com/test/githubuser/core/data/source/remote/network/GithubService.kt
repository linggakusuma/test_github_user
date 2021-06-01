package com.test.githubuser.core.data.source.remote.network

import com.test.githubuser.BuildConfig
import com.test.githubuser.core.data.source.remote.response.BaseResponse
import com.test.githubuser.core.data.source.remote.response.UserResponse
import retrofit2.http.*

interface GithubService {
    @Headers("Authorization: ${BuildConfig.TOKEN_GITHUB}")
    @GET("search/users")
    suspend fun getUser(
        @Query("q") username: String,
        @Query("page") page:Int
    ): BaseResponse<UserResponse>
}