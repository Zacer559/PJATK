package com.s16941.tookandsave.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// DB entity
@Entity
data class Image(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "image_uri") val imageLocation: String?,
    @ColumnInfo(name = "note") val note: String?,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "loc") val loc: String?,
    @ColumnInfo(name = "lat") val lat: String?,
    @ColumnInfo(name = "lon") val lon: String?
)