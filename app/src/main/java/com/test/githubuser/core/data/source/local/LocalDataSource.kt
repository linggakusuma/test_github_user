package com.test.githubuser.core.data.source.local

import com.test.githubuser.core.data.source.local.entity.SearchEntity
import com.test.githubuser.core.data.source.local.room.SearchDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val dao: SearchDao) {

    fun saveKeyword(searchEntity: SearchEntity) {
        dao.insertSearchKeyword(searchEntity)
    }

    fun getKeywords(): Flow<List<SearchEntity>> = dao.getFavoriteMovies()

    fun deleteKeyword(searchEntity: SearchEntity){
        dao.deleteKeyword(searchEntity)
    }
}