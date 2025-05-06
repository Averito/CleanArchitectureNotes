package com.averito.mimi.di

import com.averito.mimi.core.repositories.NoteRepository
import com.averito.mimi.core.services.NoteService
import com.averito.mimi.core.utils.AppLogger
import com.averito.mimi.data.services.note.LocalNoteServiceImpl
import com.averito.mimi.data.services.note.RemoteNoteServiceImpl
import com.averito.mimi.di.qualifiers.DefaultAppLogger
import com.averito.mimi.di.qualifiers.note.LocalNoteRepository
import com.averito.mimi.di.qualifiers.note.LocalNoteService
import com.averito.mimi.di.qualifiers.note.RemoteNoteRepository
import com.averito.mimi.di.qualifiers.note.RemoteNoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    @LocalNoteService
    fun provideLocalNoteService(
        @LocalNoteRepository repository: NoteRepository,
        @DefaultAppLogger defaultAppLogger: AppLogger
    ): NoteService {
        return LocalNoteServiceImpl(repository, defaultAppLogger)
    }

    @Provides
    @Singleton
    @RemoteNoteService
    fun provideRemoteNoteService(
        @RemoteNoteRepository repository: NoteRepository,
        @DefaultAppLogger defaultAppLogger: AppLogger
    ): NoteService {
        return RemoteNoteServiceImpl(repository, defaultAppLogger)
    }
}
