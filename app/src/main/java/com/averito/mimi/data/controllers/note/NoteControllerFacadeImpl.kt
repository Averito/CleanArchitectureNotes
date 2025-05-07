package com.averito.mimi.data.controllers.note

import com.averito.mimi.core.controllers.NoteController
import com.averito.mimi.core.models.NoteModel
import com.averito.mimi.core.utils.AppLogger
import com.averito.mimi.core.utils.NetworkChecker
import com.averito.mimi.di.qualifiers.DefaultAppLogger
import com.averito.mimi.di.qualifiers.note.LocalNoteController
import com.averito.mimi.di.qualifiers.note.RemoteNoteController
import javax.inject.Inject

class NoteControllerFacadeImpl @Inject constructor(
    @RemoteNoteController private val remoteNoteController: NoteController,
    @LocalNoteController private val localNoteController: NoteController,
    @DefaultAppLogger private val defaultAppLogger: AppLogger,
    private val networkChecker: NetworkChecker
) : NoteController {
    override suspend fun getAll(): List<NoteModel>? {
        val localNotes = localNoteController.getAll().orEmpty()
        val isOnline = networkChecker.isOnline()

        defaultAppLogger.debug("NoteControllerFacadeImpl getAll: Подключение к интернету - $isOnline")
        if (!isOnline) return localNotes

        val remoteNotes = remoteNoteController.getAll().orEmpty()
        val merged = (localNotes + remoteNotes).distinctBy { it.id }

        return merged
    }

    override suspend fun getOne(id: Long): NoteModel? {
        val local = localNoteController.getOne(id)
        if (local != null) return local

        val isOnline = networkChecker.isOnline()

        defaultAppLogger.debug("NoteControllerFacadeImpl getOne: Подключение к интернету - $isOnline")
        if (!isOnline) return null

        return remoteNoteController.getOne(id)
    }


    override suspend fun create(note: NoteModel): Long {
        return localNoteController.create(note)
    }

    override suspend fun update(note: NoteModel) {
        localNoteController.update(note)
    }

    override suspend fun remove(id: Long) {
        localNoteController.remove(id)
    }
}