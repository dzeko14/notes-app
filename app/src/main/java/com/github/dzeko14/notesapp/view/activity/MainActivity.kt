package com.github.dzeko14.notesapp.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.github.dzeko14.notesapp.R
import com.github.dzeko14.notesapp.view.adapter.NotesListAdapter
import com.github.dzeko14.notesapp.viewmodel.MainViewModel
import com.github.dzeko14.notesapp.viewmodel.factory.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView

    private val mAdapter: NotesListAdapter = NotesListAdapter()
    private lateinit var mMainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMainViewModel = ViewModelProviders.of(this, MainViewModelFactory())
            .get(MainViewModel::class.java)

        setupRecyclerView()

        getNotes()
    }

    private fun getNotes() {
        mMainViewModel.requestNotes()
        mMainViewModel.notes.observe(this, Observer {
            it?.let {
                mAdapter.submitList(it)
            }
        })
    }

    private fun setupRecyclerView() {
        mRecyclerView = findViewById(R.id.recycler_view)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter
    }


}
