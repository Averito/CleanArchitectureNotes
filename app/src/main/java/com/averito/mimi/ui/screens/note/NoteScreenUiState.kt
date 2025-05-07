package com.averito.mimi.ui.screens.note

import com.averito.mimi.core.models.NoteModel
import com.averito.mimi.data.models.note.LocalNoteModel

data class NoteScreenUiState(val note: NoteModel = LocalNoteModel(0, "", ""), val loading: Boolean = false, val isReadOnly: Boolean = true)
