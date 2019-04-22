package com.github.dzeko14.notesapp.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.dzeko14.notesapp.database.AppDatabase
import com.github.dzeko14.notesapp.model.Note
import com.github.dzeko14.notesapp.task.GetNoteTask

class NoteDetailsViewModel(
    db: AppDatabase
) : ViewModel() {
    private val mNoteDao = db.notesDao()

    var note: MutableLiveData<Note> = MutableLiveData<Note>()
        .apply { value = Note(-1, "") }
        private set

    fun onNotesIdReceived(id: Long) {
        if (id == -1L) return

        GetNoteTask(mNoteDao) { note.value = it }
            .execute(id)
    }
}