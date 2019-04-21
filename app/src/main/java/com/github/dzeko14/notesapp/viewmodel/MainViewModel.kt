package com.github.dzeko14.notesapp.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.github.dzeko14.notesapp.database.AppDatabase
import com.github.dzeko14.notesapp.model.Note

class MainViewModel(
    db: AppDatabase
) : ViewModel() {
    private val noteDao = db.notesDao()

    var notes: LiveData<PagedList<Note>> = MutableLiveData()

//    init {
//        getMockNotes()
//    }

//    private fun getMockNotes(){
//        val list = listOf<Note>(
//            Note(0, "wasegssegsrdvbj kskjsr vkjsrv srjhv srhvksrvh ksvhskuvh skrvh srkvuhsrkuv hsrkvuh srvuk hsrvkusrh kusrh kusrvh ksuv hksu vhs"),
//            Note(0, "wasegssegsrdvbj kskjsr vkjsrv srjhv srhvksrvh ksvhskuvh skrvh srkvuhsrkuv hsrkvuh srvuk hsrvkusrh kusrh kusrvh ksuv hksu vhs"),
//            Note(0, "wasegssegsrdvbj kskjsr vkjsrv srjhv srhvksrvh ksvhskuvh skrvh srkvuhsrkuv hsrkvuh srvuk hsrvkusrh kusrh kusrvh ksuv hksu vhs f ffh fh  fhh hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhfffhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh")
//        )
//
//        val r = Runnable { list.map {
//            noteDao.insert(it)
//        } }
//
//        Thread(r).start()
//    }

    fun requestNotes() {
        notes = LivePagedListBuilder(noteDao.getAll(), 20)
            .build()

    }
}