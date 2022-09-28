package com.s16941.tookandsave.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

//Queries to db
@Dao
interface ImageDao {
    @Query("SELECT * FROM image")
    fun getAll(): List<Image>

    @Query("SELECT * FROM image WHERE id LIKE :id")
    fun findById(id: Int): Image

    @Insert
    fun insertAll(vararg images: Image)

    @Query("DELETE FROM image WHERE id = :userId")
    fun deleteByUserId(userId: Long)


}