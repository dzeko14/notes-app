package com.github.dzeko14.notesapp.task

import android.os.AsyncTask
import com.github.dzeko14.notesapp.database.dao.NoteDao
import com.github.dzeko14.notesapp.model.Note
import java.lang.IllegalStateException

class SaveNoteTask(
    private val noteDao: NoteDao
    ) : AsyncTask<Note, Unit, Unit>() {

    override fun doInBackground(vararg params: Note?) {
        if(params.isEmpty() && params.first() == null) throw IllegalStateException("There must be some note!")
        val note = params.first()!!
        noteDao.insert(note)
    }
}