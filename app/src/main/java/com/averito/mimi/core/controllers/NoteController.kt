package com.averito.mimi.core.controllers

import com.averito.mimi.core.models.NoteModel

interface NoteController {
    suspend fun getAll(): List<NoteModel>?
    suspend fun getOne(id: Long): NoteModel?
    suspend fun create(note: NoteModel): Long
    suspend fun update(note: NoteModel): Unit
    suspend fun remove(id: Long): Unit
}