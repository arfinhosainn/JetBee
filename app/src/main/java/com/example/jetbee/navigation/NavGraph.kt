package com.example.jetbee.navigation

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.jetbee.presentaion.cart_screen.components.CartScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController = rememberAnimatedNavController(),
) {

    AnimatedNavHost(
        navController = navController,
        startDestination = Screens.FireSignInScreen.route
    ) {
        composable(route = Screens.FireSignInScreen.route) {
            // SplashScreen()
            //SignInScreen(navController = navController)
        }
        composable(route = Screens.FireSignUpScreen.route) {
          //  SignUpScreen(navController = navController, navigator = navigator)
        }
        composable(route = Screens.Details.route, arguments = listOf(
            navArgument("name"){
                type = NavType.StringType
            }

        )) {
           // DetailScreen(product = state.detailData!!)
        }
        composable(
            route = Screens.HomeScreen.route,
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -300 },
                    animationSpec = tween(300, easing = FastOutSlowInEasing)
                ) +
                        fadeOut(animationSpec = tween(300))
            },
            popEnterTransition = {
                fadeIn(animationSpec = tween(300)) + slideInHorizontally(
                    initialOffsetX = { -300 },
                    animationSpec = tween(300, easing = LinearEasing)
                )

            }
        ) {
            //HomeScreen(navController = navController, )
        }
        composable(route = Screens.CartScreen.route) {
            CartScreen(navController = navController)
        }
    }

}
