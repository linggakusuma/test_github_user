package com.test.githubuser.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.test.githubuser.core.data.source.local.entity.SearchEntity

@Database(
    entities = [SearchEntity::class],
    version = 2,
    exportSchema = false
)
abstract class GithubDatabase : RoomDatabase() {
    abstract fun searchDao(): SearchDao
}