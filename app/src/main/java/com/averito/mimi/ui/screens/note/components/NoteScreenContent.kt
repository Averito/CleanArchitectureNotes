package com.averito.mimi.ui.screens.note.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.averito.mimi.core.models.NoteModel
import com.averito.mimi.data.models.note.LocalNoteModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreenContent(
    note: NoteModel,
    isLoading: Boolean,
    isReadOnly: Boolean,
    toBack: () -> Unit,
    saveNote: () -> Unit,
    updateNote: (note: NoteModel) -> Unit
) {
    Scaffold(
        topBar = {
            Surface(shadowElevation = 4.dp) {
                TopAppBar(
                    title = { Text(if (isReadOnly) "Просмотр" else "Изменение") },
                    navigationIcon = {
                        IconButton(onClick = toBack) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                        }
                    },
                    actions = {
                        if (isReadOnly) return@TopAppBar
                        when {
                            isLoading -> {
                                Box(
                                    modifier = Modifier
                                        .padding(end = 16.dp)
                                        .size(24.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(16.dp),
                                        strokeWidth = 2.dp
                                    )
                                }
                            }
                            else -> {
                                IconButton(onClick = saveNote) {
                                    Icon(Icons.Default.Check, contentDescription = "Сохранить")
                                }
                            }
                        }
                    }
                )
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = note.title,
                onValueChange = {
                    updateNote(LocalNoteModel(id = note.id, title = it, body = note.body))
                },
                label = { Text("Заголовок") },
                modifier = Modifier.fillMaxWidth(),
                enabled = !isReadOnly
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = note.body,
                onValueChange = {
                    updateNote(LocalNoteModel(id = note.id, title = note.title, body = it))
                },
                label = { Text("Содержание") },
                enabled = !isReadOnly,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }
    }
}
