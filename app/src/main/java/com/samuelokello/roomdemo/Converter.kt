package com.samuelokello.roomdemo

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Converter {

    @SuppressLint("NewApi")
    @TypeConverter
    fun fromDate(date: LocalDate): String{
        val formarter = DateTimeFormatter.ISO_DATE
        return formarter.format(date)
    }

    @SuppressLint("NewApi")
    @TypeConverter
    fun fromString(dateString: String): LocalDate{
        val formarter = DateTimeFormatter.ISO_DATE
        return LocalDate.parse(dateString,formarter)
    }
}