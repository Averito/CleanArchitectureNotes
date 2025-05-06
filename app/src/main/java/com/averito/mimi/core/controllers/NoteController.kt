package com.averito.mimi.core.controllers

import com.averito.mimi.core.models.NoteModel

interface NoteController {
    suspend fun getAll(): List<NoteModel>?
    suspend fun getOne(id: Int): NoteModel?
    suspend fun create(note: NoteModel): Unit
    suspend fun update(note: NoteModel): Unit
    suspend fun remove(id: Int): Unit
}