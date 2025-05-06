package com.averito.mimi.core.repositories

import com.averito.mimi.core.models.NoteModel

interface NoteRepository {
    suspend fun getAll(): Result<List<NoteModel>>
    suspend fun getOne(id: Int): Result<NoteModel?>
    suspend fun create(note: NoteModel): Result<Unit>
    suspend fun update(note: NoteModel): Result<Unit>
    suspend fun remove(id: Int): Result<Unit>
}