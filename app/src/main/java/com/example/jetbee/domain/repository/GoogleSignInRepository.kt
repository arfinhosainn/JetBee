package com.example.jetbee.domain.repository

import com.example.jetbee.domain.model.AuthUser
import com.example.jetbee.util.Resource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow

interface GoogleSignInRepository {

    suspend fun addUserToFireStore()

    fun signInWithCredential(
        credentials: AuthCredential,
        user: AuthUser
    ): Flow<Resource<AuthResult>>

}