package com.s16941.tookandsave.db

import androidx.room.Database
import androidx.room.RoomDatabase

// DB abstract class
@Database(entities = [Image::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        val DATABASE_NAME = "appdb"
    }

    abstract fun imageDao(): ImageDao
}