package com.example.jetbee.presentaion.detail_screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetbee.domain.model.Product
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun MainDetailScreen(
    name: String,
    viewModel: ProductDetailViewModel = hiltViewModel(),
    product: Product
) {
    val productDetail  = viewModel.product.value

    DetailScreen(product = product)
}
