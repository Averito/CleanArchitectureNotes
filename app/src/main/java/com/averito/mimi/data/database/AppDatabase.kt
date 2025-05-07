package com.averito.mimi.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.averito.mimi.data.daos.note.NoteDao
import com.averito.mimi.data.models.note.LocalNoteModel

@Database(
    entities = [LocalNoteModel::class],
    version = 7,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}
