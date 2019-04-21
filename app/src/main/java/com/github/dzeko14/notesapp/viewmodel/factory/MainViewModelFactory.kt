package com.github.dzeko14.notesapp.viewmodel.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.dzeko14.notesapp.database.AppDatabase
import com.github.dzeko14.notesapp.viewmodel.MainViewModel

class MainViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(AppDatabase.instance) as T
    }
}