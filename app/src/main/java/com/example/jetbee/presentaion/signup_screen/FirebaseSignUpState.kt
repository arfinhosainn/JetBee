package com.example.jetbee.presentaion.signup_screen

data class FirebaseSignUpState(
    var isLoading: Boolean = false,
    var isSignedUp: String? = null,
    val error: String? = null
)

