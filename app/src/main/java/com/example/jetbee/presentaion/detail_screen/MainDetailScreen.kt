package com.example.jetbee.presentaion.detail_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetbee.domain.model.Product
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun MainDetailScreen(
    name: String,
    product: Product,
    navController: NavController
) {

    DetailScreen(product = product, navController = navController)
}
