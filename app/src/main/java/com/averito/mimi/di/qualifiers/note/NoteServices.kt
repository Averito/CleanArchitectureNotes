package com.averito.mimi.di.qualifiers.note

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalNoteService

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteNoteService