package com.test.githubuser.core.domain.usecase

import com.test.githubuser.core.data.source.local.entity.SearchEntity
import com.test.githubuser.core.domain.model.Search
import com.test.githubuser.core.domain.repositroy.IGithubRepository
import kotlinx.coroutines.flow.Flow

class GithubInteractor(private val iGithubRepository: IGithubRepository) : GithubUseCase {

    override suspend fun getGithubUser(username: String) =
        iGithubRepository.getUserGithub(username)

    override fun saveKeyword(search: SearchEntity) {
        iGithubRepository.saveKeyword(search)
    }

    override suspend fun getKeyword(): Flow<List<Search>> = iGithubRepository.getKeyword()

    override fun deleteKeyword(search: Search) {
        iGithubRepository.deleteKeyword(search)
    }
}