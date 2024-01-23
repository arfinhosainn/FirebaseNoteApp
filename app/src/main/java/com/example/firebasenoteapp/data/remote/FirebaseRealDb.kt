package com.example.firebasenoteapp.data.remote

import com.example.firebasenoteapp.data.model.Note
import com.example.firebasenoteapp.util.Resource
import com.google.firebase.database.DataSnapshot
import kotlinx.coroutines.flow.Flow

interface FirebaseRealDb {


    suspend fun saveNotes(note: Note): Resource<Void>
    fun getAllNotes(): Flow<Resource<List<Note>>>

}