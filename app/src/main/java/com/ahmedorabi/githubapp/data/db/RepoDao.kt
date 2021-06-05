package com.ahmedorabi.githubapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ahmedorabi.githubapp.data.model.Item

@Dao
interface RepoDao {

    @Insert
    suspend fun insertRepos(items: List<Item>)

    @Query("Select * from repo where name LIKE :q")
    fun getAllRepos(q: String): List<Item>


    @Query("Delete from repo where name =:q")
    suspend fun deleteAllRepos(q: String)
}