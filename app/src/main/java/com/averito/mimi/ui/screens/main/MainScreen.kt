package com.averito.mimi.ui.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.averito.mimi.core.models.NoteModel
import com.averito.mimi.data.models.note.LocalNoteModel
import com.averito.mimi.data.models.note.RemoteNoteModel
import com.averito.mimi.ui.screens.main.components.MainScreenContent
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel<MainScreenViewModel>(),
    toNotePage: (Long) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    fun onAdd(note: NoteModel) {
        coroutineScope.launch {
            val newNoteId = viewModel.addNote(note)
            toNotePage(newNoteId)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadNotes()
    }

    MainScreenContent(
        isLoading = uiState.loading,
        notes = uiState.notes,
        onNoteClick = toNotePage,
        onRemoveClick = { viewModel.removeNote(it) },
        onAddClick = { onAdd(LocalNoteModel(id = 0, title = "", body = "")) }
    )
}

@Composable
@Preview(showBackground = true)
fun MainScreenContentPreview() {
    MainScreenContent(
        isLoading = false,
        notes = listOf(
            LocalNoteModel(id = 1, title = "Привет", body = "Это первая заметка"),
            LocalNoteModel(id = 2, title = "Compose", body = "Jetpack Compose — топ за свои деньги"),
            LocalNoteModel(id = 3, title = "Заметка 2", body = "Большое количество текстаааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа"),
            LocalNoteModel(id = 4, title = "Заметка 3", body = "Большое количество текстаааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааааа"),
            RemoteNoteModel(id = 5, title = "Remote 1", body = "Remote desc 1")
        ),
        onNoteClick = {},
        onRemoveClick = {},
        onAddClick = {}
    )
}

