package com.test.githubuser.core.data

import androidx.paging.PagingData
import com.test.githubuser.core.data.source.local.LocalDataSource
import com.test.githubuser.core.data.source.local.entity.SearchEntity
import com.test.githubuser.core.data.source.remote.RemoteDataSource
import com.test.githubuser.core.domain.model.Search
import com.test.githubuser.core.domain.model.User
import com.test.githubuser.core.domain.repositroy.IGithubRepository
import com.test.githubuser.utils.AppExecutors
import com.test.githubuser.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GithubRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IGithubRepository {

    override suspend fun getUserGithub(username: String): Flow<PagingData<User>> =
        remoteDataSource.fetchGithubUsers(username).map {
            DataMapper.responseToDomainUsers(it)
        }

    override fun saveKeyword(search: SearchEntity) {
        appExecutors.diskIO().execute { localDataSource.saveKeyword(search) }
    }

    override suspend fun getKeyword(): Flow<List<Search>> =
        localDataSource.getKeywords().map {
            DataMapper.entityToDomainSearch(it)
        }

    override fun deleteKeyword(search: Search) {
        appExecutors.diskIO()
            .execute { localDataSource.deleteKeyword(DataMapper.domainToEntitySearch(search)) }
    }
}