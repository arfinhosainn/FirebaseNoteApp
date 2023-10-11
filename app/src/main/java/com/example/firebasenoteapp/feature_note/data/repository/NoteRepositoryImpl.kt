package com.example.firebasenoteapp.feature_note.data.repository

import android.util.Log
import com.example.firebasenoteapp.feature_note.domain.model.Note
import com.example.firebasenoteapp.feature_note.domain.repository.NoteRepository
import com.example.firebasenoteapp.feature_note.domain.util.toMap
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.snapshots
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class NoteRepositoryImpl(
    private val firestore: FirebaseFirestore
) : NoteRepository {
    override fun getNotes(): Flow<List<Note>> {
        val notesRef = Firebase.firestore.collection("notes")
        val query = notesRef.orderBy("createdAt")
        val flow = query.snapshots().map { snapshot ->
            snapshot.map { snapshot ->
                snapshot.toObject(Note::class.java)
            }
        }
        return flow
    }

    override suspend fun insertNote(note: Note) {
        val notesRef = Firebase.firestore.collection("notes")
        val noteMap = note.toMap()
        notesRef.add(noteMap).addOnSuccessListener { document ->
            Log.d("Note Added", "insertNote: Successfully added note with ID")
        }.addOnFailureListener {
            Log.d("Note Added", "insertNote: Failed to insert note ")
        }
    }

    override suspend fun getNoteById(id: Int): Note? {
        val notesRef = Firebase.firestore.collection("notes")
        val noteDoc = notesRef.document(id.toString())

        val snapshot = noteDoc.get().await()
        return if (snapshot.exists()) {
            snapshot.toObject(Note::class.java)
        } else {
            null
        }
    }
}