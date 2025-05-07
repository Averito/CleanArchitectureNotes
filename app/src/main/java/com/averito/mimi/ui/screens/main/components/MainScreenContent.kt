package com.averito.mimi.ui.screens.main.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.averito.mimi.core.models.NoteModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    isLoading: Boolean,
    notes: List<NoteModel>,
    onNoteClick: (Long) -> Unit,
    onRemoveClick: (Long) -> Unit,
    onAddClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            Surface(shadowElevation = 4.dp) {
                TopAppBar(title = { Text("Заметки") })
            }
        },
        floatingActionButton = {
            FloatingActionButton (onClick = onAddClick) {
                Icon(Icons.Default.Add, contentDescription = "Добавить")
            }
        }
    ) { padding ->
        when {
            notes.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    when {
                        isLoading -> {
                            CircularProgressIndicator()
                        }
                        else -> {
                            Text("Нет заметок", fontSize = 24.sp)
                        }
                    }
                }
            }
            else -> {
                LazyColumn(
                    contentPadding = padding,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(notes) { note ->
                        NoteItem(note, onClick = onNoteClick, onRemove = onRemoveClick)
                    }
                }
            }
        }
    }
}
