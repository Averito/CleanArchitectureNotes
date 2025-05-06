package com.averito.mimi.data.mappers

import com.averito.mimi.core.models.NoteModel
import com.averito.mimi.data.models.note.LocalNoteModel
import com.averito.mimi.data.models.note.RemoteNoteModel

fun NoteModel.toLocalModel(): LocalNoteModel = LocalNoteModel(id + 100, title, body)