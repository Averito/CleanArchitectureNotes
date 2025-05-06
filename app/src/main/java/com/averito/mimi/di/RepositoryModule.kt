package com.averito.mimi.di

import com.averito.mimi.core.repositories.NoteRepository
import com.averito.mimi.data.daos.note.NoteDao
import com.averito.mimi.data.repositories.note.LocalNoteRepositoryImpl
import com.averito.mimi.data.repositories.note.RemoteNoteRepositoryImpl
import com.averito.mimi.di.qualifiers.note.LocalNoteRepository
import com.averito.mimi.di.qualifiers.note.RemoteNoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    @LocalNoteRepository
    fun provideLocalNoteRepository(
        dao: NoteDao
    ): NoteRepository {
        return LocalNoteRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    @RemoteNoteRepository
    fun provideRemoteNoteRepository(
        retrofit: Retrofit
    ): NoteRepository {
        return RemoteNoteRepositoryImpl(retrofit)
    }
}
