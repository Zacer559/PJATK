package com.s16941.tookandsave

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.s16941.tookandsave.db.AppDatabase
import com.s16941.tookandsave.db.Image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteViewModel : ViewModel() {
    val note = MutableLiveData<String>()

    init {
        note.value = ""
    }

    fun launchInsertToDb(context: Context, image: Image) {
        viewModelScope.launch {
            insertToDb(context, image)
        }
    }

    // inserts to db
    suspend fun insertToDb(context: Context, image: Image) = withContext(Dispatchers.Default) {
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
        val dao = db.imageDao()
        dao.insertAll(image)
    }

}

