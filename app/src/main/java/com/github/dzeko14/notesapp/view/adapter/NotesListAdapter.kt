package com.github.dzeko14.notesapp.view.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.dzeko14.notesapp.model.Note

class NotesListAdapter(
    private val callback: (Long) -> Unit
)
    : PagedListAdapter<Note, NotesListAdapter.NotesViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NotesViewHolder(
            layoutInflater.inflate(
                android.R.layout.simple_list_item_1,
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
        private val textView: TextView = v.findViewById(android.R.id.text1)

        fun onBind(note: Note?){
            if (note == null) {
                textView.text = ""
                itemView.setOnClickListener {  }
            } else {
               val text = if (note.text.length > 100) { cutText(note.text) }
                else { note.text }
                textView.text = text
                itemView.setOnClickListener { callback(note.id) }
            }
        }

        private fun cutText(text: String): String {
            return "${text.substring(0..100)}..."
        }

    }
}