package com.averito.mimi.data.models.note

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.averito.mimi.core.models.NoteModel

@Entity(tableName = "notes")
data class LocalNoteModel(
    @PrimaryKey override val id: Int,
    override val title: String,
    override val body: String
): NoteModel
