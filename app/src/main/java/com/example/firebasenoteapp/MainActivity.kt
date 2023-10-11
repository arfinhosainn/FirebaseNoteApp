package com.example.firebasenoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.firebasenoteapp.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.example.firebasenoteapp.ui.theme.FirebaseNoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseNoteAppTheme {
                AddEditNoteScreen(noteColor = 232)
            }
        }
    }
}