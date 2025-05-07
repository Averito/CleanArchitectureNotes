package com.averito.mimi.ui.screens.main

import com.averito.mimi.core.models.NoteModel

data class MainScreenUiState(
    val notes: List<NoteModel>,
    val loading: Boolean
)
