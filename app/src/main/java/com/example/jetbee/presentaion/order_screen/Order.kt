package com.example.jetbee.presentaion.order_screen

import com.google.firebase.firestore.ServerTimestamp
import java.security.Timestamp

data class Order(

    val productTitle: String = "",
    val productPrice: Int = 0,
    var productImagesUrls: List<String?> = listOf(),

    var orderSize: String = "",
    var orderColor: String = "",
    var orderQuantity: Int = 0,
    var orderPrice: Int = 0,

    var userId: String = "",
    val userName: String = "",
    val userEmail: String = "",
    val userPhoneNumber: String = "",

    @ServerTimestamp
    val createdDate: Timestamp? = null,

    )