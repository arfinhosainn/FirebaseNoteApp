package com.example.firebasenoteapp.di

import com.example.firebasenoteapp.feature_note.data.repository.NoteRepositoryImpl
import com.example.firebasenoteapp.feature_note.domain.repository.NoteRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesFirestore() = Firebase.firestore

    @Singleton
    @Provides
    fun providesNoteRepository(firestore: FirebaseFirestore): NoteRepository {
        return NoteRepositoryImpl(firestore)
    }
}