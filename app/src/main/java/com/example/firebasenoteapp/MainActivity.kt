package com.example.firebasenoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebasenoteapp.presentation.addeditnote_screen.AddEditNoteScreen
import com.example.firebasenoteapp.presentation.note_screen.NoteScreen
import com.example.firebasenoteapp.ui.theme.FirebaseNoteAppTheme
import com.example.firebasenoteapp.util.Screen
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseNoteAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Screen.NotesScreen.route
                ) {
                    composable(route = Screen.NotesScreen.route) {
                        NoteScreen(navController = navController)
                    }
                    composable(
                        route = Screen.AddEditNoteScreen.route
                    ) {
                        AddEditNoteScreen(navController = navController)
                    }
                }

            }
        }
    }
}