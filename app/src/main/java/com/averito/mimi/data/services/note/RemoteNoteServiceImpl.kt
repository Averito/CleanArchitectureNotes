package com.averito.mimi.data.services.note

import com.averito.mimi.core.models.NoteModel
import com.averito.mimi.core.repositories.NoteRepository
import com.averito.mimi.core.services.NoteService
import com.averito.mimi.core.utils.AppLogger
import com.averito.mimi.di.qualifiers.DefaultAppLogger
import com.averito.mimi.di.qualifiers.note.RemoteNoteRepository
import javax.inject.Inject

class RemoteNoteServiceImpl @Inject constructor(
    @RemoteNoteRepository private val noteRepository: NoteRepository,
    @DefaultAppLogger private val defaultAppLogger: AppLogger
) : NoteService {

    override suspend fun getAll(): Result<List<NoteModel>> {
        defaultAppLogger.debug("RemoteNoteServiceImpl: Получение сетевых заметок.")
        return noteRepository.getAll()
    }

    override suspend fun getOne(id: Int): Result<NoteModel?> {
        defaultAppLogger.debug("RemoteNoteServiceImpl: Получение сетевых заметок с id = $id.")
        return noteRepository.getOne(id)
    }

    override suspend fun create(note: NoteModel): Result<Unit> {
        defaultAppLogger.info("RemoteNoteServiceImpl: Создание сетевой заметки: title = \"${note.title}\".")
        return noteRepository.create(note)
    }

    override suspend fun update(note: NoteModel): Result<Unit> {
        defaultAppLogger.info("RemoteNoteServiceImpl: Обновление сетевой заметки: id = ${note.id}, title = \"${note.title}\".")
        return noteRepository.update(note)
    }

    override suspend fun remove(id: Int): Result<Unit> {
        defaultAppLogger.warn("RemoteNoteServiceImpl: Удаление сетевой заметки с id = $id.")
        return noteRepository.remove(id)
    }
}
