package com.samuelokello.roomdemo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey (autoGenerate = true) val uId: Int = 0,
    @ColumnInfo("first_name") val firstName: String?,
    @ColumnInfo("last_name") val lastName: String?
)

