package com.example.jetbee.presentaion.detail_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetbee.domain.model.Product

@Composable
fun MainDetailScreen(
    detailViewModel: DetailViewModel,
    navController: NavController,
) {
    val productDetail = detailViewModel.product.value

    DetailScreen(product = productDetail, navController = navController)
}
