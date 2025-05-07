package com.averito.mimi.core.models

interface NoteModel {
    val id: Long
    val title: String
    val body: String
    val isRemote: Boolean
}