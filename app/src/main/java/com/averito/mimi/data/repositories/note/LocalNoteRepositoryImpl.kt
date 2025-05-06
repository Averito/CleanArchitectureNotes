package com.averito.mimi.data.repositories.note

import com.averito.mimi.core.models.NoteModel
import com.averito.mimi.core.repositories.NoteRepository
import com.averito.mimi.data.daos.note.NoteDao
import com.averito.mimi.data.mappers.toLocalModel

class LocalNoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    override suspend fun getAll(): Result<List<NoteModel>> {
        try {
            val notes = noteDao.getAll()
            return Result.success(notes)
        } catch (error: Exception) {
            return Result.failure(error)
        }
    }

    override suspend fun getOne(id: Int): Result<NoteModel?> {
        try {
            val note = noteDao.getOne(id)
            return Result.success(note)
        } catch (error: Exception) {
            return Result.failure(error)
        }
    }

    override suspend fun create(note: NoteModel): Result<Unit> {
        try {
            noteDao.create(note.toLocalModel())
            return Result.success(Unit)
        } catch (error: Exception) {
            return Result.failure(error)
        }
    }

    override suspend fun update(note: NoteModel): Result<Unit> {
        try {
            noteDao.update(note.toLocalModel())
            return Result.success(Unit)
        } catch (error: Exception) {
            return Result.failure(error)
        }
    }

    override suspend fun remove(id: Int): Result<Unit> {
        try {
            noteDao.remove(id)
            return Result.success(Unit)
        } catch (error: Exception) {
            return Result.failure(error)
        }
    }

}