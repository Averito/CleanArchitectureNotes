package com.averito.mimi.data.services.note

import com.averito.mimi.core.models.NoteModel
import com.averito.mimi.core.repositories.NoteRepository
import com.averito.mimi.core.services.NoteService
import com.averito.mimi.core.utils.AppLogger
import com.averito.mimi.di.qualifiers.DefaultAppLogger
import com.averito.mimi.di.qualifiers.note.LocalNoteRepository
import javax.inject.Inject

class LocalNoteServiceImpl @Inject constructor(
    @LocalNoteRepository private val noteRepository: NoteRepository,
    @DefaultAppLogger private val defaultAppLogger: AppLogger
) : NoteService {

    override suspend fun getAll(): Result<List<NoteModel>> {
        defaultAppLogger.debug("LocalNoteServiceImpl: Получение локальных заметок.")
        return noteRepository.getAll()
    }

    override suspend fun getOne(id: Long): Result<NoteModel?> {
        defaultAppLogger.debug("LocalNoteServiceImpl: Получение локальных заметок с id = $id.")
        return noteRepository.getOne(id)
    }

    override suspend fun create(note: NoteModel): Result<Long> {
        defaultAppLogger.info("LocalNoteServiceImpl: Создание локальной заметки: title = \"${note.title}\".")
        return noteRepository.create(note)
    }

    override suspend fun update(note: NoteModel): Result<Unit> {
        defaultAppLogger.info("LocalNoteServiceImpl: Обновление локальной заметки: id = ${note.id}, title = \"${note.title}\".")
        return noteRepository.update(note)
    }

    override suspend fun remove(id: Long): Result<Unit> {
        defaultAppLogger.warn("LocalNoteServiceImpl: Удаление локальной заметки с id = $id.")
        return noteRepository.remove(id)
    }
}
