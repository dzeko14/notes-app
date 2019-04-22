package com.github.dzeko14.notesapp.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.TextView
import com.github.dzeko14.notesapp.R
import com.github.dzeko14.notesapp.view.adapter.NotesListAdapter
import com.github.dzeko14.notesapp.viewmodel.ASCENDING
import com.github.dzeko14.notesapp.viewmodel.DESCENDING
import com.github.dzeko14.notesapp.viewmodel.MainViewModel
import com.github.dzeko14.notesapp.viewmodel.factory.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mFAB: FloatingActionButton
    private lateinit var mSearchView: SearchView
    private lateinit var mEmptyListTextView: TextView

    private val mAdapter: NotesListAdapter = NotesListAdapter(::onNotesListItemClicked)
    private lateinit var mMainViewModel: MainViewModel

    private fun onNotesListItemClicked(id: Long) {
        val intent = Intent(this, NoteDetailsActivity::class.java)
            .apply { putExtra(NOTE_ID, id) }
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.note_list_menu, menu)
        mSearchView = menu.findItem(R.id.search).actionView as SearchView
        setupSearchView()
        return super.onCreateOptionsMenu(menu)
    }

    private fun setupSearchView() {
        mSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String): Boolean {
                return true
            }

            override fun onQueryTextChange(text: String): Boolean {
                mMainViewModel.searchNotes(text)
                return true
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        item?.let {
            when(item.itemId) {
                R.id.asc -> {
                    mMainViewModel.notes.removeObservers(this)
                    mMainViewModel.getSortedNotesListBy(ASCENDING)
                }
                R.id.desc -> {
                    mMainViewModel.notes.removeObservers(this)
                    mMainViewModel.getSortedNotesListBy(DESCENDING)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mMainViewModel = ViewModelProviders.of(this, MainViewModelFactory())
            .get(MainViewModel::class.java)

        mEmptyListTextView = findViewById(R.id.empty_list_text)

        setupRecyclerView()

        setupFAB()
        observeNotesUpdate()
        getNotes()
    }

    private fun observeNotesUpdate() {
        mMainViewModel.notesFlag.observe(this, Observer {
            mMainViewModel.notes.observe(this, Observer { notes ->
                notes?.let {
                    mAdapter.submitList(notes)
                }
                val notesListSize = notes?.size ?: 0
                mEmptyListTextView.visibility = if (notesListSize == 0) View.VISIBLE
                else View.GONE
            })
        })
    }

    private fun setupFAB() {
        mFAB = findViewById(R.id.FAB)
        mFAB.setOnClickListener {
            startActivity(
                Intent(this@MainActivity, NoteDetailsActivity::class.java)
            )
        }
    }

    private fun getNotes() {
        mMainViewModel.requestNotes()

    }

    private fun setupRecyclerView() {
        mRecyclerView = findViewById(R.id.recycler_view)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter
    }


}
