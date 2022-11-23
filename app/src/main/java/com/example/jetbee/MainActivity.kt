package com.example.jetbee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.jetbee.navigation.NavigationGraph
import com.example.jetbee.presentaion.NavGraphs
import com.example.jetbee.ui.theme.JetBeeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetBeeTheme {
                NavigationGraph()
             DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}

