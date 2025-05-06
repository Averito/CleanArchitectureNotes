package com.averito.mimi.di

import com.averito.mimi.core.controllers.NoteController
import com.averito.mimi.core.services.NoteService
import com.averito.mimi.core.utils.AppLogger
import com.averito.mimi.core.utils.NetworkChecker
import com.averito.mimi.data.controllers.note.LocalNoteControllerImpl
import com.averito.mimi.data.controllers.note.NoteControllerFacadeImpl
import com.averito.mimi.data.controllers.note.RemoteNoteControllerImpl
import com.averito.mimi.di.qualifiers.DefaultAppLogger
import com.averito.mimi.di.qualifiers.note.LocalNoteController
import com.averito.mimi.di.qualifiers.note.LocalNoteService
import com.averito.mimi.di.qualifiers.note.NoteControllerFacade
import com.averito.mimi.di.qualifiers.note.RemoteNoteController
import com.averito.mimi.di.qualifiers.note.RemoteNoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ControllerModule {
    @Provides
    @Singleton
    @LocalNoteController
    fun provideLocalNoteController(
        @LocalNoteService noteService: NoteService,
        @DefaultAppLogger appLogger: AppLogger
    ): NoteController {
        return LocalNoteControllerImpl(noteService, appLogger)
    }

    @Provides
    @Singleton
    @RemoteNoteController
    fun provideRemoteNoteController(
        @RemoteNoteService noteService: NoteService,
        @DefaultAppLogger appLogger: AppLogger
    ): NoteController {
        return RemoteNoteControllerImpl(noteService, appLogger)
    }

    @Provides
    @Singleton
    @NoteControllerFacade
    fun provideNoteControllerFacade(
        @RemoteNoteController remoteNoteController: NoteController,
        @LocalNoteController localNoteController: NoteController,
        @DefaultAppLogger defaultAppLogger: AppLogger,
        networkChecker: NetworkChecker
    ): NoteController {
        return NoteControllerFacadeImpl(remoteNoteController, localNoteController, defaultAppLogger, networkChecker)
    }
}