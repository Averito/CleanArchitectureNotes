package com.averito.mimi.data.daos.note

import androidx.room.*
import com.averito.mimi.data.models.note.LocalNoteModel

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    suspend fun getAll(): List<LocalNoteModel>

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getOne(id: Int): LocalNoteModel?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun create(note: LocalNoteModel)

    @Update
    suspend fun update(note: LocalNoteModel)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun remove(id: Int)

}
