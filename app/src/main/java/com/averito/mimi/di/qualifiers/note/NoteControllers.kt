package com.averito.mimi.di.qualifiers.note

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalNoteController

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteNoteController

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NoteControllerFacade