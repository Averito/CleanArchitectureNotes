package com.averito.mimi.di.qualifiers.note

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalNoteRepository

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteNoteRepository