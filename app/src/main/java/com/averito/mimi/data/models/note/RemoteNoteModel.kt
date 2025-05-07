package com.averito.mimi.data.models.note

import com.averito.mimi.core.models.NoteModel

data class RemoteNoteModel(
    override val id: Long,
    override val title: String,
    override val body: String,
    override val isRemote: Boolean = true
) : NoteModel
