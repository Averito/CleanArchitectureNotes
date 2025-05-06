package com.averito.mimi.di

import android.content.Context
import androidx.room.Room
import com.averito.mimi.data.daos.note.NoteDao
import com.averito.mimi.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "mimi_database"
        ).build()
    }

    @Provides
    fun provideNoteDao(
        db: AppDatabase
    ): NoteDao = db.noteDao()
}
