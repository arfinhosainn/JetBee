package com.example.jetbee.presentaion.cart_screen

import androidx.compose.runtime.Composable
import com.example.jetbee.domain.model.CartProducts

data class CartCoffeeState(
    val isLoading: Boolean = true,
    val cartCoffee: List<CartProducts> = emptyList(),
    val error: String? = null,


    )