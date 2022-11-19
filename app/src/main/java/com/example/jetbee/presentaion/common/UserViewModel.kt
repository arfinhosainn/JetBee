package com.example.jetbee.presentaion.common

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetbee.domain.model.AuthUser
import com.example.jetbee.domain.model.CartProducts
import com.example.jetbee.domain.repository.FirebaseRepository
import com.example.jetbee.presentaion.cart_screen.CartState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    private val _userState = mutableStateOf(AuthUser())
    val userState: State<AuthUser> = _userState

    private val currentUser = firebaseRepository.currentUser()
    private val userId = currentUser?.uid

    init {
        getUserdata()
    }

    private fun getUserdata() {
        viewModelScope.launch {
            val getUserDataResult = userId.let {
                firebaseRepository.getUserData(it.toString())
            }
            getUserDataResult?.let {
                _userState.value = it
            }
        }
    }

    fun updateUserData() {
        viewModelScope.launch {
            getUserdata()
        }
    }

     fun deleteCoffeeFromCart(cartProducts: CartProducts) {
        // val userId = currentUser?.uid
        viewModelScope.launch {
            firebaseRepository.deleteCoffeeFromCart(userId.toString(), cartProduct = cartProducts)
        }
    }


}