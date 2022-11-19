package com.example.jetbee.presentaion.cart_screen

data class CartState(
    val productDeleted: Boolean = false,
    val coffeeAddedToCart: String? = "",
    val error: String? = "",
    val isLoading: Boolean = false
)
