package com.example.jetbee.presentaion.detail_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jetbee.domain.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : ViewModel() {

    private val _product = mutableStateOf(Product())
    val product: State<Product> = _product

    fun setProduct(product: Product) {
        _product.value = product
    }


}