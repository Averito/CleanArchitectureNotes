package com.averito.mimi.data.mappers

import com.averito.mimi.core.models.NoteModel
import com.averito.mimi.data.models.note.LocalNoteModel
import com.averito.mimi.data.models.note.RemoteNoteModel

fun NoteModel.toLocalModel(): LocalNoteModel = LocalNoteModel(id, title, body)

fun RemoteNoteModel.toNoteModel(): NoteModel {
    return object : NoteModel {
        override val id: Long = this@toNoteModel.id
        override val title: String = this@toNoteModel.title
        override val body: String = this@toNoteModel.body
        override val isRemote: Boolean = true
    }
}