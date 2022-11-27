package com.example.jetbee.data.remote.repositoryImpl

import com.example.jetbee.domain.repository.GoogleSignInRepository
import com.example.jetbee.util.Constant.USER_COLLECTION
import com.example.jetbee.util.Resource
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue.serverTimestamp
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Named

class GoogleSignInRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth,
    private var oneTapClient: SignInClient,
    @Named("sign_in_request")
    private var signInRequest: BeginSignInRequest,
    @Named("sign_up_request")
    private var signUpRequest: BeginSignInRequest
) : GoogleSignInRepository {
    override fun oneTapSignInWithGoogle(): Flow<Resource<BeginSignInResult>> {
        return flow {
            emit(Resource.Loading())
            val result = oneTapClient.beginSignIn(signInRequest).await()
            emit(Resource.Success(result))
        }.catch {
            try {
                val signUpResult = oneTapClient.beginSignIn(signUpRequest).await()
                emit(Resource.Success(signUpResult))
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
            }
        }
    }

    override suspend fun firebaseSingnInWithGoogle(googleCredential: AuthCredential): Resource<Boolean> {
        return try {
            val authResult = auth.signInWithCredential(googleCredential).await()
            val isNewUser = authResult.additionalUserInfo?.isNewUser ?: false
            if (isNewUser) {
                addUserToFireStore()
            }
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error(result = e.message.toBoolean(), message = "")
        }
    }

    override suspend fun addUserToFireStore() {
        auth.currentUser?.apply {
            val user = toUser()
            firestore.collection(USER_COLLECTION).document(uid).set(user).await()

        }
    }


    private fun FirebaseUser.toUser() = mapOf(
        "displayname" to displayName,
        "email" to email,
        "photourl" to photoUrl?.toString(),
        "createdat" to serverTimestamp()
    )


}