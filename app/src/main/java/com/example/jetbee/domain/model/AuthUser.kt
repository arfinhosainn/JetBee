package com.example.jetbee.domain.model


data class AuthUser(
     val email: String = "",
     val password: String = "",
     var id: String = "",
     val name: String = "",
     val phoneNumber: String = "",
     val cartProducts: List<CartProducts> = listOf(),
     val activeOrders: List<Product> = listOf(),
 )