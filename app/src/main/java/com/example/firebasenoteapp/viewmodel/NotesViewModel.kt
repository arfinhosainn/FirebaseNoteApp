package com.example.firebasenoteapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebasenoteapp.data.remote.FirebaseRealDb
import com.example.firebasenoteapp.presentation.note_screen.NotesState
import com.example.firebasenoteapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NotesViewModel @Inject constructor(
    private val firebaseDatabase: FirebaseRealDb
) : ViewModel() {


    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    init {
        getNotes()
    }


    private fun getNotes() = viewModelScope.launch {
        firebaseDatabase.getAllNotes().let { notes ->

            when (notes) {
                is Resource.Loading -> {
                    _state.value = NotesState(isLoading = true)
                }

                is Resource.Success -> {
                    _state.value = NotesState(notes = notes.data ?: emptyList())
                }

                is Resource.Error -> {
                    _state.value = NotesState(error = notes.message ?: "Unknown error")
                }

            }

        }


    }


}


