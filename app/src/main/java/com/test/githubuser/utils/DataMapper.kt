package com.test.githubuser.utils

import androidx.paging.PagingData
import androidx.paging.map
import com.test.githubuser.core.data.source.local.entity.SearchEntity
import com.test.githubuser.core.data.source.remote.response.UserResponse
import com.test.githubuser.core.domain.model.Search
import com.test.githubuser.core.domain.model.User

object DataMapper {

    fun responseToDomainUsers(input: PagingData<UserResponse>): PagingData<User> {
        return input.map {
            User(
                login = it.login,
                id = it.id,
                avatar = it.avatar
            )
        }
    }

    fun entityToDomainSearch(input: List<SearchEntity>): List<Search> {
        val data = ArrayList<Search>()
        input.map {
            val search = Search(
                it.id,
                it.keyword
            )
            data.add(search)
        }
        return data
    }

    fun domainToEntitySearch(input: Search) = SearchEntity(
        id = input.id ?: 0,
        keyword = input.keyword
    )
}