package com.example.jetbee.presentaion.order_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val useCase: OrderUseCase
) :ViewModel() {

    val isLoading = mutableStateOf(false)

    private fun setLoadingState(boolean: Boolean){
        isLoading.value = boolean
    }

    fun addOrders(list: MutableList<Order>, navController: NavController) {
        viewModelScope.launch {
            setLoadingState(true)
            useCase.addOrders(
                list = list,
                navController = navController,
                ::setLoadingState
            )
        }
    }
}