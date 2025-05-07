package com.averito.mimi.data.daos.note

import androidx.room.*
import com.averito.mimi.data.models.note.LocalNoteModel

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    suspend fun getAll(): List<LocalNoteModel>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getOne(id: Long): LocalNoteModel?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun create(note: LocalNoteModel): Long

    @Update
    suspend fun update(note: LocalNoteModel)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun remove(id: Long)

}
