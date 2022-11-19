package com.example.jetbee.presentaion.cart_screen

import com.example.jetbee.domain.model.CartProducts

sealed class CartEvents {
    data class DeleteCoffee(val coin: CartProducts) : CartEvents()
    object Nothing : CartEvents()

}