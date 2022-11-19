package com.example.jetbee.data.remote.repositoryImpl

import okio.utf8Size

fun main() {
    val price = listOf("1.34", "29", "342", "34", "453")
    val fd = price.map {
        it.toFloat()
    }
   val total = fd.sum()

    println(total)
}