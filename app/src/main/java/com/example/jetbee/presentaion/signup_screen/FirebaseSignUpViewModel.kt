package com.example.jetbee.presentaion.signin_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetbee.domain.model.AuthUser
import com.example.jetbee.domain.repository.FirebaseRepository
import com.example.jetbee.presentaion.signup_screen.FirebaseSignUpState
import com.example.jetbee.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirebaseSignupViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    private val _signUpState = Channel<FirebaseSignUpState>()
    val signUpState = _signUpState.receiveAsFlow()


    fun createUser(user: AuthUser) = viewModelScope.launch {
        firebaseRepository.firebaseSignUp(user).collect { result ->
            when (result) {
                is Resource.Loading -> {
                    _signUpState.send(FirebaseSignUpState(isLoading = true))
                }
                is Resource.Success -> {
                    _signUpState.send(FirebaseSignUpState(isSignedUp = "Signed In Successful"))
                }
                is Resource.Error -> {
                    _signUpState.send(FirebaseSignUpState(error = result.message))
                }
            }
        }
    }
}