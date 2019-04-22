package com.github.dzeko14.notesapp.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.github.dzeko14.notesapp.database.converter.DateConverter
import com.github.dzeko14.notesapp.database.dao.NoteDao
import com.github.dzeko14.notesapp.model.Note

@Database(
    entities = [
        Note::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    value = [
        DateConverter::class
    ]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao(): NoteDao

    companion object {
        lateinit var instance: AppDatabase

        fun init(context: Context) {
            instance = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "NoteDB"
            ).build()
        }
    }
}