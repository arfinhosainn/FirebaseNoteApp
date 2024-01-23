package com.example.firebasenoteapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebasenoteapp.data.remote.FirebaseRealDb
import com.example.firebasenoteapp.presentation.note_screen.NotesState
import com.example.firebasenoteapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NotesViewModel @Inject constructor(
    private val firebaseDatabase: FirebaseRealDb
) : ViewModel() {


    private val _state = MutableStateFlow(NotesState())
    val state: StateFlow<NotesState> = _state


     fun getNotes() {
        viewModelScope.launch {
            firebaseDatabase.getAllNotes().collectLatest { notes ->
                when (notes) {
                    is Resource.Success -> {
                        Log.d("notes", "realnote: ${notes.data}")
                        _state.value = NotesState(notes = notes.data ?: emptyList())
                    }

                    is Resource.Loading -> {
                        _state.value = NotesState(isLoading = true)
                    }

                    is Resource.Error -> {
                        _state.value = NotesState(error = notes.message ?: "Unknown error")
                    }

                }

            }

        }


    }


}


