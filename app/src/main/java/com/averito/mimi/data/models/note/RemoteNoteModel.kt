package com.averito.mimi.data.models.note

import com.averito.mimi.core.models.NoteModel

data class RemoteNoteModel(
    override val id: Int,
    override val title: String,
    override val body: String
) : NoteModel
