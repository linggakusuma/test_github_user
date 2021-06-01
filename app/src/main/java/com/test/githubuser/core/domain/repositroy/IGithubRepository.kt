package com.test.githubuser.core.domain.repositroy

import androidx.paging.PagingData
import com.test.githubuser.core.data.source.local.entity.SearchEntity
import com.test.githubuser.core.domain.model.Search
import com.test.githubuser.core.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IGithubRepository {
    suspend fun getUserGithub(username: String): Flow<PagingData<User>>
    fun saveKeyword(search: SearchEntity)
    suspend fun getKeyword(): Flow<List<Search>>
    fun deleteKeyword(search: Search)
}