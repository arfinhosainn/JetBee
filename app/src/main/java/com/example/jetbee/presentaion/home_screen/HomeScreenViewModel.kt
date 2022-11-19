package com.example.jetbee.presentaion.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetbee.domain.repository.FirebaseRepository
import com.example.jetbee.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val firebaseRepository: FirebaseRepository
) : ViewModel() {

    private val _getAllCoffee: MutableStateFlow<GetAllCoffeeState> = MutableStateFlow(
        GetAllCoffeeState()
    )
    val getAllCoffee: StateFlow<GetAllCoffeeState> = _getAllCoffee

    init {
        getAllCoffee()
    }

    private fun getAllCoffee() = viewModelScope.launch {
        firebaseRepository.getAllCoffee().let { result ->
            when(result){
                is Resource.Success -> {
                    _getAllCoffee.value = GetAllCoffeeState(coffeeSuccess = result.data)
                }
                is Resource.Loading -> {
                    _getAllCoffee.value = GetAllCoffeeState(isLoading = true)
                }
                is Resource.Error -> {
                    _getAllCoffee.value = GetAllCoffeeState(error = result.message)
                }
            }
        }
    }
}