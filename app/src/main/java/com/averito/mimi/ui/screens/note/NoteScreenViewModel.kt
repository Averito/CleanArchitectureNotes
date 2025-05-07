package com.averito.mimi.ui.screens.note

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
class NoteScreenViewModel @Inject constructor(
    @NoteControllerFacade private val noteControllerFacade: NoteController
) : ViewModel() {
    private val _uiState = MutableStateFlow(NoteScreenUiState())
    val uiState = _uiState.asStateFlow()

    fun loadNote(id: Long) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true)
            val note = noteControllerFacade.getOne(id)
            if (note == null) return@launch
            _uiState.value = _uiState.value.copy(note = note, loading = false, isReadOnly = note.isRemote)
        }
    }

    fun updateNote(note: NoteModel) {
        _uiState.value = _uiState.value.copy(note = note)
    }

    suspend fun saveNoteAsync() {
        _uiState.value = _uiState.value.copy(loading = true)
        noteControllerFacade.update(_uiState.value.note)
        _uiState.value = _uiState.value.copy(loading = false)
    }

    fun saveNote() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true)
            noteControllerFacade.update(_uiState.value.note)
            _uiState.value = _uiState.value.copy(loading = false)
        }
    }
}