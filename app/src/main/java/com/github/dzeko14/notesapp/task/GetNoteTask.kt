package com.github.dzeko14.notesapp.task

import android.os.AsyncTask
import com.github.dzeko14.notesapp.database.dao.NoteDao
import com.github.dzeko14.notesapp.model.Note
import java.lang.IllegalStateException

class GetNoteTask(
    private val noteDao: NoteDao,
    private val callback: (Note) -> Unit
) : AsyncTask<Long, Unit, Note>() {

    override fun doInBackground(vararg params: Long?): Note {
        if(params.isEmpty() && params.first() == null) throw IllegalStateException("There must be some id!")
        return noteDao.getById(params.first()!!)
    }

    override fun onPostExecute(result: Note) {
        callback(result)
    }
}