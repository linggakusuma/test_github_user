package com.test.githubuser.core.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.test.githubuser.core.data.source.remote.network.GithubService
import com.test.githubuser.core.data.source.remote.response.UserResponse

class PagingDataSource(private val service: GithubService, private val username: String) : PagingSource<Int,UserResponse>() {

    override fun getRefreshKey(state: PagingState<Int, UserResponse>): Int = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponse> {
        return try {
            val currentLoadingPageKey = params.key ?: 1
            val response = service.getUser(username,currentLoadingPageKey)
            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            LoadResult.Page(
                data = response.items,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}