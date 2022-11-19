package com.example.jetbee.presentaion.home_screen

import com.example.jetbee.domain.model.Product

data class GetAllCoffeeState(
    val isLoading: Boolean = false,
    val coffeeSuccess: List<Product>? = emptyList(),
    val error: String? = ""
)
