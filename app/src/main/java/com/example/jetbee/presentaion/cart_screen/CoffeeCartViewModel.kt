package com.example.jetbee.presentaion.cart_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetbee.domain.model.CartProducts
import com.example.jetbee.domain.repository.FirebaseRepository
import com.example.jetbee.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoffeeCartViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    private val _cartState = mutableStateOf(CartState())
    val cartState: State<CartState> = _cartState

    private val currentUser = firebaseRepository.currentUser()


    suspend fun addCoffeeToCart(cartProduct: CartProducts) {
        if (currentUser != null) {
            val uid = currentUser.uid
            viewModelScope.launch {
                firebaseRepository.addCoffeeToCart(cartProduct, uid).collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            _cartState.value =
                                CartState(coffeeAddedToCart = "Coffee Added Successfully")
                        }
                        is Resource.Error -> {
                            _cartState.value = CartState(error = "Sorry!! There was a problem")
                        }
                        is Resource.Loading -> {
                            _cartState.value = CartState(isLoading = true)
                        }
                    }
                }
            }
        }
    }
}