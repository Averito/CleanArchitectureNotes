package com.averito.mimi.ui.screens.note

import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.averito.mimi.data.models.note.LocalNoteModel
import com.averito.mimi.ui.screens.note.components.NoteScreenContent
import kotlinx.coroutines.launch

@Composable
fun NoteScreen(
    viewModel: NoteScreenViewModel = hiltViewModel<NoteScreenViewModel>(),
    toBack: () -> Unit,
    noteId: Long?
) {
    val uiState by viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    fun toBackProxy() {
        coroutineScope.launch {
            if (!uiState.isReadOnly) viewModel.saveNoteAsync()
            toBack()
        }
    }

    LaunchedEffect(noteId) {
        if (noteId == null) return@LaunchedEffect
        viewModel.loadNote(noteId)
    }

    NoteScreenContent(
        note = uiState.note,
        isLoading = uiState.loading,
        isReadOnly = uiState.isReadOnly,
        toBack = { toBackProxy() },
        saveNote = { toBackProxy() },
        updateNote = { viewModel.updateNote(it) }
    )
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    NoteScreenContent(
        note = LocalNoteModel(1L, "Заметка 1", "Какой-то текст, много текста много текстамного текста много текста много текстамного текстамного текстамного текстамного текстамного текстамного текста"),
        isLoading = false,
        isReadOnly = false,
        toBack = {},
        saveNote = {},
        updateNote = {}
    )
}