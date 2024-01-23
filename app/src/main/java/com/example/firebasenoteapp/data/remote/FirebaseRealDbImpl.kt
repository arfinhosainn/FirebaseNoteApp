package com.example.firebasenoteapp.data.remote

import android.util.Log
import com.example.firebasenoteapp.data.model.Note
import com.example.firebasenoteapp.util.Resource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRealDbImpl @Inject constructor(
    private val dbReference: DatabaseReference
) : FirebaseRealDb {
    override suspend fun saveNotes(note: Note): Resource<Void> {
        return try {
            val result = dbReference.child("Notes").push().setValue(note).await()
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown Error")
        }
    }


    override fun getAllNotes(): Flow<Resource<List<Note>>> = flow {
        emit(Resource.Loading())
        try {
            val notes = dbReference.child("Notes").get().await().children.mapNotNull {
                it.getValue(Note::class.java)
            }
            Log.d("notes", "notwo: $notes")
            emit(Resource.Success(notes))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: ""))
        }
    }
}