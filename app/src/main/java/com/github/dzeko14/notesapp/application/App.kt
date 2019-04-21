package com.github.dzeko14.notesapp.application

import android.app.Application
import com.github.dzeko14.notesapp.database.AppDatabase

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(applicationContext)
    }

}