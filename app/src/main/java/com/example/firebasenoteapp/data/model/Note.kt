package com.example.firebasenoteapp.data.model

import com.example.firebasenoteapp.ui.theme.BabyBlue
import com.example.firebasenoteapp.ui.theme.LightGreen
import com.example.firebasenoteapp.ui.theme.RedOrange
import com.example.firebasenoteapp.ui.theme.RedPink
import com.example.firebasenoteapp.ui.theme.Violet
import java.util.UUID
import kotlin.random.Random


data class Note(
    val title: String,
    val content: String,
    val timestamp: Long,
    val color: Int,
    val id: Int? = Random.nextInt()
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}