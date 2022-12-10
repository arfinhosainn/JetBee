import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.jetbee.navigation.Screens
import com.example.jetbee.presentaion.cart_screen.components.CartScreen
import com.example.jetbee.presentaion.detail_screen.DetailViewModel
import com.example.jetbee.presentaion.detail_screen.MainDetailScreen
import com.example.jetbee.presentaion.home_screen.HomeScreen
import com.example.jetbee.presentaion.profile_screen.ProfileScreen
import com.example.jetbee.presentaion.signin_screen.OneTapSignInViewModel
import com.example.jetbee.presentaion.signin_screen.SignInScreen
import com.example.jetbee.presentaion.signup_screen.SignUpScreen
import com.example.jetbee.presentaion.splash_screen.SplashScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    detailViewModel: DetailViewModel,
    oneTapSignInViewModel: OneTapSignInViewModel
) {

    AnimatedNavHost(
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {
        composable(route = Screens.FireSignInScreen.route) {
            SignInScreen(
                navController = navController,
                oneTapSignInViewModel = oneTapSignInViewModel
            )
        }
        composable(route = Screens.FireSignUpScreen.route) {
            SignUpScreen(navController = navController)
        }
        composable(route = Screens.Details.route) {
            Log.d("Args", it.arguments?.getString(it.toString()).toString())
            MainDetailScreen(navController = navController, detailViewModel = detailViewModel)

        }
        composable(route = Screens.SplashScreen.route) {
            SplashScreen(navController = navController)
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
            HomeScreen(navController = navController, detailViewModel = detailViewModel)
        }
        composable(route = Screens.CartScreen.route) {
            CartScreen(navController = navController)
        }
        composable(route = Screens.Profile.route) {
            ProfileScreen(navController = navController)
        }
    }

}