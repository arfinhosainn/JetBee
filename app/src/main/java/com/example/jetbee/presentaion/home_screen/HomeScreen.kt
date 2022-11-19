package com.example.jetbee.presentaion.home_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.jetbee.navigation.BottomNavItem
import com.example.jetbee.navigation.BottomNavigationBar
import com.example.jetbee.navigation.Screens
import com.example.jetbee.presentaion.common.UserViewModel
import com.example.jetbee.presentaion.home_screen.components.HeaderItem
import com.example.jetbee.presentaion.home_screen.components.SearchBox
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination(start = true)
@Composable
fun HomeScreen(navigator: DestinationsNavigator,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel(),
    navController: NavHostController,

) {
    val state = viewModel.getAllCoffee.collectAsState()
    val state2 = userViewModel.userState.value

    Scaffold(bottomBar = {
        BottomNavigationBar(
            items = listOf(
                BottomNavItem(
                    name = "Home",
                    icon = com.example.jetbee.R.drawable.home,
                    route = Screens.HomeScreen.route
                ),
                BottomNavItem(
                    name = "Cart",
                    icon = com.example.jetbee.R.drawable.cart,
                    route = Screens.CartScreen.route,
                    badgeCount = state2.cartProducts.size
                ),
                BottomNavItem(
                    name = "Favourite",
                    icon = com.example.jetbee.R.drawable.heart,
                    route = Screens.Favourite.route
                ),
                BottomNavItem(
                    name = "Profile",
                    icon = com.example.jetbee.R.drawable.profile,
                    route = Screens.Profile.route
                ),
            ),
            navController = navController,
            onItemClick = {
                navController.navigate(it.route)
            }
        )

    }, content = { paddingValues ->
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 18.dp, end = 18.dp, top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HeaderItem()
                Spacer(modifier = Modifier.height(26.dp))
                SearchBox()
                Spacer(modifier = Modifier.height(26.dp))
                if (state.value.coffeeSuccess?.isNotEmpty() == true) {
                    AllCoffeeContent(
                        product = state.value.coffeeSuccess!!,
                        navController = navController, navigator = navigator
                    )
                }
            }
        }
    })


}

