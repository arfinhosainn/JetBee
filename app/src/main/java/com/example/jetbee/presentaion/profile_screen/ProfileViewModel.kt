package com.example.jetbee.presentaion.profile_screen

import androidx.lifecycle.ViewModel
import com.example.jetbee.domain.repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    fun signOut() = firebaseRepository.signOut()


}