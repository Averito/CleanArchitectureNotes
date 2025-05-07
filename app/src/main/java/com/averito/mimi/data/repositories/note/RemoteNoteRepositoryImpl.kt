package com.averito.mimi.data.repositories.note

import com.averito.mimi.core.models.NoteModel
import com.averito.mimi.core.repositories.NoteRepository
import com.averito.mimi.data.mappers.toNoteModel
import com.averito.mimi.data.models.note.RemoteNoteModel
import retrofit2.Retrofit
import retrofit2.http.*
import javax.inject.Inject

class RemoteNoteRepositoryImpl @Inject constructor(
    retrofit: Retrofit
) : NoteRepository {

    private val client = retrofit.create(JsonPlaceholderClient::class.java)

    override suspend fun getAll(): Result<List<NoteModel>> = try {
        val notes = client.getAll()
        Result.success(notes.map { it.toNoteModel() })
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getOne(id: Long): Result<NoteModel?> = try {
        val note = client.getOne(id)
        Result.success(note?.toNoteModel())
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun create(note: NoteModel): Result<Long> {
        return Result.failure(UnsupportedOperationException("Создание заметок в удалённом репозитории не поддерживается."))
    }


    override suspend fun update(note: NoteModel): Result<Unit> {
        return Result.failure(UnsupportedOperationException("Обновление заметок в удалённом репозитории не поддерживается."))
    }

    override suspend fun remove(id: Long): Result<Unit> {
        return Result.failure(UnsupportedOperationException("Удаление заметок в удалённом репозитории не поддерживается."))
    }

    private interface JsonPlaceholderClient {
        @GET("posts")
        suspend fun getAll(): List<RemoteNoteModel>

        @GET("posts/{id}")
        suspend fun getOne(@Path("id") id: Long): RemoteNoteModel?
    }
}
