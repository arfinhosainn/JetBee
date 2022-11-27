package com.example.jetbee.presentaion.signin_screen

import com.google.android.gms.auth.api.identity.BeginSignInResult

data class OneTapSignInState(
    val isLoading: Boolean? = false,
    val success: BeginSignInResult? = null,
    val error: String? = null
)
