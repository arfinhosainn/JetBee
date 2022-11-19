package com.example.jetbee.presentaion.detail_screen

import com.example.jetbee.domain.model.Product

data class DetailScreenState(
    val isLoading: Boolean  = false,
    val detailData: Product? = null ,
     val error : String? = ""
)
