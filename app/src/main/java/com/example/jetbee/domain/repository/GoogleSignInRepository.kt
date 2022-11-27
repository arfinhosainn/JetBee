package com.example.jetbee.domain.repository

import com.example.jetbee.util.Resource
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.firebase.auth.AuthCredential
import kotlinx.coroutines.flow.Flow

interface GoogleSignInRepository {
    fun oneTapSignInWithGoogle(): Flow<Resource<BeginSignInResult>>

  suspend  fun firebaseSingnInWithGoogle(googleCredential:AuthCredential): Resource<Boolean>

    suspend fun addUserToFireStore()

}