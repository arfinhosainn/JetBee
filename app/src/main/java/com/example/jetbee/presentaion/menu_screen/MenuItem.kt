package com.example.jetbee.presentaion.menu_screen

import androidx.compose.ui.graphics.vector.ImageVector

data class MenuItem(
    val direction: String ,
    val title: String,
    val icon: ImageVector,
    val contentDescription:String
)