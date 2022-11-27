package com.example.jetbee

import NavigationGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import com.example.jetbee.presentaion.detail_screen.DetailViewModel
import com.example.jetbee.ui.theme.JetBeeTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val productDetailViewModel = viewModels<DetailViewModel>()

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBeeTheme {
                val navController = rememberAnimatedNavController()
            
            NavigationGraph(navController = navController, detailViewModel = productDetailViewModel.value)
            }
        }
    }
}

