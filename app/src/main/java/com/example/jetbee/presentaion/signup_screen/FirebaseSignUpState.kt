package com.example.jetbee.presentaion.signup_screen

data class FirebaseSignUpState(
    var isLoading: Boolean = false,
    var isSignedIn: String? = null,
    val error: String? = null
)

