package com.example.firebasenoteapp.data.remote

import android.util.Log
import com.example.firebasenoteapp.data.model.Note
import com.example.firebasenoteapp.util.Resource
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseRealDbImpl @Inject constructor(
    private val dbReference: DatabaseReference
) : FirebaseRealDb {
    override fun saveNotes(note: Note): Flow<Resource<Unit>> = flow {
        emit(Resource.Loading())
        try {
            dbReference.child("Notes").setValue(note).await()
            Log.d("notes", "getNote: $note")
            emit(Resource.Success(Unit))
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "Unknown Error"))
        }
    }

    override suspend fun getAllNotes(): Resource<List<Note>> {
        return try {
            val notes = dbReference.child("Notes").get().await().children.mapNotNull {
                it.getValue(Note::class.java)
            }
            Resource.Success(notes)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "")
        }
    }

    override suspend fun getNote(id: Int): Resource<Note> {
        return try {
            val dataSnapShot = dbReference.child("Notes").child(id.toString()).get().await()
            val note = dataSnapShot.getValue(Note::class.java)
            if (note != null) {
                Resource.Success(note)
            } else {
                Resource.Error("Note not found")
            }
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown Error")
        }
    }
}