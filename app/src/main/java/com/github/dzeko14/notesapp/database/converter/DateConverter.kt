package com.github.dzeko14.notesapp.database.converter

import android.arch.persistence.room.TypeConverter
import java.util.*


class DateConverter {

    @TypeConverter
    fun convertDateToLong(date: Date) : Long {
        return date.time
    }

    @TypeConverter
    fun convertLongToDate(time: Long) : Date{
        return Date(time)
    }
}