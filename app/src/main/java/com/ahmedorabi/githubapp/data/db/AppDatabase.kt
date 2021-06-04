package com.ahmedorabi.githubapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmedorabi.githubapp.data.model.Item

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun repoDao(): RepoDao
}