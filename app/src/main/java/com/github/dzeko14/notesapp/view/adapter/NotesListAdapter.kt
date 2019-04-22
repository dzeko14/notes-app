package com.github.dzeko14.notesapp.view.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.dzeko14.notesapp.R
import com.github.dzeko14.notesapp.model.Note
import java.text.SimpleDateFormat
import java.util.*

class NotesListAdapter(
    private val callback: (Long) -> Unit
)
    : PagedListAdapter<Note, NotesListAdapter.NotesViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NotesViewHolder(
            layoutInflater.inflate(
                R.layout.notes_list_item,
                parent,
                false
            ),
            callback
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int)
            = holder.onBind(getItem(position))

    companion object{
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
                return oldItem == newItem
            }
        }
    }

    class NotesViewHolder(
        v: View,
        private val callback: (Long) -> Unit
    ) : RecyclerView.ViewHolder(v) {
        private val textView: TextView = v.findViewById(R.id.text)
        private val dateTextView: TextView = v.findViewById(R.id.date)

        fun onBind(note: Note?){
            if (note == null) {
                textView.text = ""
                dateTextView.text = " "
                itemView.setOnClickListener {  }
            } else {
               val text = if (note.text.length > 100) { cutText(note.text) }
                else { note.text }
                textView.text = text
                dateTextView.text = note.date.getFormatDate()
                itemView.setOnClickListener { callback(note.id) }
            }
        }

        private fun cutText(text: String): String {
            return "${text.substring(0..100)}..."
        }

    }
}

fun Date.getFormatDate(): String {
    val formatter = SimpleDateFormat("dd:MM:YYY HH:mm", Locale.getDefault())
    return formatter.format(this)
}