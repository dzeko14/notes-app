package com.github.dzeko14.notesapp.view.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.github.dzeko14.notesapp.R
import com.github.dzeko14.notesapp.viewmodel.NoteDetailsViewModel
import com.github.dzeko14.notesapp.viewmodel.factory.NoteDetailsViewModelFactory

const val NOTE_ID = "note_id"

class NoteDetailsActivity : AppCompatActivity() {

    private lateinit var mNoteEditText: EditText

    private lateinit var mViewModel: NoteDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_details)

        mNoteEditText = findViewById(R.id.editText)

        mViewModel = ViewModelProviders
            .of(this, NoteDetailsViewModelFactory())
            .get(NoteDetailsViewModel::class.java)

        getNote()

        mViewModel.note.observe(this, Observer {
            mNoteEditText.setText(it?.text ?: "")
        })
    }

    override fun onBackPressed() {
        saveNote()
        super.onBackPressed()

    }

    override fun onSupportNavigateUp(): Boolean {
        saveNote()
        return super.onSupportNavigateUp()
    }

    private fun saveNote() {
        mViewModel.onNoteSave(mNoteEditText.text.toString())
    }

    private fun getNote() {
        val id = intent.getLongExtra(NOTE_ID, -1)
        mViewModel.onNotesIdReceived(id)
    }

}
