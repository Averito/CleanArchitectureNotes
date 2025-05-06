package com.averito.mimi.data.models.note.dto

import com.averito.mimi.data.models.note.LocalNoteModel

data class CreateNoteDto(
    val title: String,
    val body: String
) {
    fun toNoteModel(): LocalNoteModel {
        return LocalNoteModel(
            id = 0,
            title = this.title,
            body = this.body
        )
    }
}
