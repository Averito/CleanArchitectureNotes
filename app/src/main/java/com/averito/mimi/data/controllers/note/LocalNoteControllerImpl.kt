package com.averito.mimi.data.controllers.note

import com.averito.mimi.core.controllers.NoteController
import com.averito.mimi.core.models.NoteModel
import com.averito.mimi.core.services.NoteService
import com.averito.mimi.core.utils.AppLogger
import com.averito.mimi.di.qualifiers.DefaultAppLogger
import com.averito.mimi.di.qualifiers.note.LocalNoteRepository
import javax.inject.Inject

class LocalNoteControllerImpl @Inject constructor(
    @LocalNoteRepository private val noteService: NoteService,
    @DefaultAppLogger private val appLogger: AppLogger
) : NoteController {
    override suspend fun getAll(): List<NoteModel>? {
        val result = noteService.getAll()

        if (result.isFailure) {
            appLogger.error("LocalNoteControllerImpl: getAll failure: ${result.exceptionOrNull()?.message}")
            return null
        }

        return result.getOrNull();
    }

    override suspend fun getOne(id: Int): NoteModel? {
        val result = noteService.getOne(id)

        if (result.isFailure) {
            appLogger.error("LocalNoteControllerImpl: getOne failure: ${result.exceptionOrNull()?.message}")
            return null
        }

        return result.getOrNull();
    }

    override suspend fun create(note: NoteModel): Unit {
        val result = noteService.create(note)

        if (result.isFailure) {
            appLogger.error("LocalNoteControllerImpl: create failure: ${result.exceptionOrNull()?.message}")
        }
    }

    override suspend fun update(note: NoteModel): Unit {
        val result = noteService.update(note)

        if (result.isFailure) {
            appLogger.error("LocalNoteControllerImpl: update failure: ${result.exceptionOrNull()?.message}")
        }
    }

    override suspend fun remove(id: Int): Unit {
        val result = noteService.remove(id)

        if (result.isFailure) {
            appLogger.error("LocalNoteControllerImpl: remove failure: ${result.exceptionOrNull()?.message}")
        }
    }
}