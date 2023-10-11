package com.example.firebasenoteapp.feature_note.domain.model

import com.example.firebasenoteapp.feature_note.domain.util.generateRandomIntId
import com.example.firebasenoteapp.ui.theme.BabyBlue
import com.example.firebasenoteapp.ui.theme.LightGreen
import com.example.firebasenoteapp.ui.theme.RedOrange
import com.example.firebasenoteapp.ui.theme.RedPink
import com.example.firebasenoteapp.ui.theme.Violet

data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String) : Exception(message)

