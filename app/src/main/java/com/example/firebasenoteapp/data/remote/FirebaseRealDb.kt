package com.example.firebasenoteapp.data.remote

import com.example.firebasenoteapp.data.model.Note
import com.example.firebasenoteapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface FirebaseRealDb {


    fun saveNotes(note: Note): Flow<Resource<Unit>>
    suspend fun getAllNotes(): Resource<List<Note>>
   suspend fun getNote(id: Int):Resource<Note>

}