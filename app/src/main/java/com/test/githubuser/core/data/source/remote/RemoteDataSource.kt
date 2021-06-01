package com.test.githubuser.core.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.test.githubuser.core.data.source.remote.network.GithubService
import com.test.githubuser.core.data.source.remote.response.UserResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.coroutineContext

class RemoteDataSource(private val service: GithubService) {


    fun fetchGithubUsers(username: String): Flow<PagingData<UserResponse>> =
        Pager(PagingConfig(pageSize = 6)) {
            PagingDataSource(service, username)
        }.flow


}