package com.github.dzeko14.notesapp.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.github.dzeko14.notesapp.database.AppDatabase
import com.github.dzeko14.notesapp.model.Note
import com.github.dzeko14.notesapp.task.GetNoteTask
import com.github.dzeko14.notesapp.task.SaveNoteTask

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

    fun onNoteSave(noteText: String) {
        if (noteText.isEmpty()) return
        val noteId: Long = if(this.note.value?.id != -1L)
            this.note.value?.id ?: 0
        else 0L
        val note = Note(noteId, noteText)
        SaveNoteTask(mNoteDao).execute(note)
    }
}