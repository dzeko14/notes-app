package com.github.dzeko14.notesapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.DataSource
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.github.dzeko14.notesapp.database.AppDatabase
import com.github.dzeko14.notesapp.model.Note

const val ASCENDING = 1
const val DESCENDING = 2

class MainViewModel(
    db: AppDatabase
) : ViewModel() {
    private val noteDao = db.notesDao()

    var notes: LiveData<PagedList<Note>> = MutableLiveData()

    val notesFlag: MutableLiveData<Boolean> = MutableLiveData()

    fun requestNotes() {
        updateNotes(noteDao.getAll())
        notifyNotesListChanged()
    }

    fun getSortedNotesListBy(order: Int) {
        val dataSource = if (order == ASCENDING) {
            noteDao.getAllOrderByDateAsc()
        } else {
            noteDao.getAllOrderByDateDesc()
        }

        updateNotes(dataSource)

        notifyNotesListChanged()
    }

    private fun notifyNotesListChanged() { notesFlag.value = !((notesFlag.value) ?: false) }


    private fun updateNotes(dataSource: DataSource.Factory<Int, Note>) {
        notes = LivePagedListBuilder(dataSource, 20)
            .build()
    }

    fun searchNotes(text: String) {
        updateNotes(noteDao.findAllBy("%$text%"))
        notifyNotesListChanged()
    }
}