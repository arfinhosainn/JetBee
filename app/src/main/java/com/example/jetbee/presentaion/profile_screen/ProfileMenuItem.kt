package com.example.jetbee.presentaion.profile_screen

data class ProfileMenuItem(
    val name: String,
    val icon: Int,
    val actionIcon: Int? = null,
    val route: String
)
