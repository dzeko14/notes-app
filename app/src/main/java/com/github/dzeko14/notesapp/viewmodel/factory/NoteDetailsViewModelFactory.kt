package com.github.dzeko14.notesapp.viewmodel.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.github.dzeko14.notesapp.database.AppDatabase
import com.github.dzeko14.notesapp.viewmodel.NoteDetailsViewModel

class NoteDetailsViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NoteDetailsViewModel(AppDatabase.instance) as T
    }
}