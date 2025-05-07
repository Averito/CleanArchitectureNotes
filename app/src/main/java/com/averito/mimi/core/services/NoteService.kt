package com.averito.mimi.core.services

import com.averito.mimi.core.models.NoteModel

interface NoteService {
    suspend fun getAll(): Result<List<NoteModel>>
    suspend fun getOne(id: Long): Result<NoteModel?>
    suspend fun create(note: NoteModel): Result<Long>
    suspend fun update(note: NoteModel): Result<Unit>
    suspend fun remove(id: Long): Result<Unit>
}