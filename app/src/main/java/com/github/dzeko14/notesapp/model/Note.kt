package com.github.dzeko14.notesapp.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val text: String,
    val date: Date
)