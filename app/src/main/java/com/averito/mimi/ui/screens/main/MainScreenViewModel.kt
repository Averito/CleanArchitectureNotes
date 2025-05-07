package com.averito.mimi.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.averito.mimi.core.controllers.NoteController
import com.averito.mimi.core.models.NoteModel
import com.averito.mimi.di.qualifiers.note.NoteControllerFacade
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    @NoteControllerFacade private val noteControllerFacade: NoteController
) : ViewModel() {
    private val _uiState = MutableStateFlow(MainScreenUiState(emptyList(), false))
    val uiState = _uiState.asStateFlow()

    init {
        loadNotes()
    }

    fun loadNotes() {
        viewModelScope.launch {
            loadNotesInternal()
        }
    }

    fun removeNote(id: Long) {
        viewModelScope.launch {
            noteControllerFacade.remove(id)
            loadNotesInternal()
        }
    }

    suspend fun addNote(note: NoteModel): Long {
        return noteControllerFacade.create(note)
    }

    private suspend fun loadNotesInternal() {
        _uiState.value = _uiState.value.copy(
            loading = true
        )
        val notes = noteControllerFacade.getAll().orEmpty()
        _uiState.value = _uiState.value.copy(
            loading = false,
            notes = notes
        )
    }
}