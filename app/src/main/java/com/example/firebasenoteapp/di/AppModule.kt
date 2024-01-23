package com.example.firebasenoteapp.di

import com.example.firebasenoteapp.data.remote.FirebaseRealDb
import com.example.firebasenoteapp.data.remote.FirebaseRealDbImpl
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideDatabaseReference(database: FirebaseDatabase): DatabaseReference {
        return database.reference
    }

    @Provides
    @Singleton
    fun provideFirebaseRealDb(db: DatabaseReference): FirebaseRealDb {
        return FirebaseRealDbImpl(db)
    }


}