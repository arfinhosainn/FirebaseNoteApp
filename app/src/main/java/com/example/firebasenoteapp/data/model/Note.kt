package com.example.firebasenoteapp.data.model

import com.example.firebasenoteapp.ui.theme.BabyBlue
import com.example.firebasenoteapp.ui.theme.LightGreen
import com.example.firebasenoteapp.ui.theme.RedOrange
import com.example.firebasenoteapp.ui.theme.RedPink
import com.example.firebasenoteapp.ui.theme.Violet
import java.util.UUID
import kotlin.random.Random


data class Note(
    val title: String = "",
    val content: String = "",
    val timestamp: Long = 0L,
    val color: Int = 0
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}