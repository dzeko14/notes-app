package com.github.dzeko14.notesapp.database.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.*
import com.github.dzeko14.notesapp.model.Note

@Dao
interface NoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: Note): Long

    @Delete
    fun delete(note: Note)

    @Update
    fun update(note: Note)

    @Query("Select * from Note Order By date Desc ")
    fun getAll(): DataSource.Factory<Int, Note>

    @Query("Select * From Note Where id = :id")
    fun getById(id: Long): Note

    @Query("Select * From Note Order By date Asc")
    fun getAllOrderByDateAsc(): DataSource.Factory<Int, Note>

    @Query("Select * From Note Order By date Desc")
    fun getAllOrderByDateDesc(): DataSource.Factory<Int, Note>

    @Query("Select * From Note Where text Like :t Order By date Desc")
    fun findAllBy(t: String): DataSource.Factory<Int, Note>
}