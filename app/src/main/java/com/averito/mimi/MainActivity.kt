package com.averito.mimi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.averito.mimi.core.controllers.NoteController
import com.averito.mimi.core.models.NoteModel
import com.averito.mimi.di.qualifiers.note.NoteControllerFacade
import com.averito.mimi.ui.theme.MimiTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MimiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NoteListScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@HiltViewModel
class NoteViewModel @Inject constructor(
    @NoteControllerFacade private val controller: NoteController
) : ViewModel() {

    private val _notes = mutableStateOf<List<NoteModel>>(emptyList())
    val notes: State<List<NoteModel>> = _notes

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    init {
        viewModelScope.launch {
            val result = controller.getAll()
            _notes.value = result ?: emptyList()
            _isLoading.value = false
        }
    }
}


@Composable
fun NoteListScreen(modifier: Modifier = Modifier, viewModel: NoteViewModel = hiltViewModel()) {
    val notes by viewModel.notes
    val isLoading by viewModel.isLoading

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator()
            }
            notes.isEmpty() -> {
                Text("Нет заметок", fontSize = 20.sp)
            }
            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(notes) { note ->
                        NoteItem(note)
                    }
                }
            }
        }
    }
}

@Composable
fun NoteItem(note: NoteModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = note.title, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = note.body, fontSize = 14.sp)
        }
    }
}

