package com.example.jetbee.presentaion.signin_screen

import androidx.activity.result.IntentSenderRequest
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetbee.domain.repository.GoogleSignInRepository
import com.example.jetbee.util.Resource
import com.google.android.gms.auth.api.identity.BeginSignInResult
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.AuthCredential
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OneTapSignInViewModel @Inject constructor(
    private val repository: GoogleSignInRepository,
    val oneTapClient: SignInClient
) : ViewModel() {





    private val _oneTapSignInState = mutableStateOf(OneTapSignInState())
    val oneTapSignInStateState: State<OneTapSignInState> = _oneTapSignInState

    private val _signInWithGoogleState = mutableStateOf(signInWithGoogleState())
    val signInWithGoogleState: State<signInWithGoogleState> = _signInWithGoogleState


    fun oneTapSignIn() = viewModelScope.launch {
        repository.oneTapSignInWithGoogle().collect {result ->

           when(result){
               is Resource.Success -> {
                   _oneTapSignInState.value = OneTapSignInState(success = result.data)
               }
               is Resource.Loading ->{
                   _oneTapSignInState.value = OneTapSignInState(isLoading = true)
               }
               is Resource.Error -> {
                   _oneTapSignInState.value = OneTapSignInState(error = result.message)
               }
           }
        }
    }

    fun signInWithGoogle(googleCredential: AuthCredential)= viewModelScope.launch {
        repository.firebaseSingnInWithGoogle(googleCredential).let {result ->
            when(result){
                is Resource.Success -> {
                    _signInWithGoogleState.value = signInWithGoogleState(success = result.data)
                }
                is Resource.Loading->{
                    _signInWithGoogleState.value = signInWithGoogleState(isLoading =  true)
                }
                is Resource.Error -> {
                    _signInWithGoogleState.value = signInWithGoogleState(error = result.message)
                }
            }

        }
    }

}