package com.example.firebasenoteapp.presentation.note_screen

import com.example.firebasenoteapp.data.model.Note

data class NotesState(
    val notes: List<Note> = emptyList(),
    val error: String = "",
    val isLoading: Boolean = false
)
