package com.test.githubuser.core.data.source.local.room

import androidx.room.*
import com.test.githubuser.core.data.source.local.entity.SearchEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSearchKeyword(searchEntity: SearchEntity)

    @Query("SELECT * FROM search")
    fun getFavoriteMovies(): Flow<List<SearchEntity>>

    @Delete
    fun deleteKeyword(searchEntity: SearchEntity)
}