package com.example.jetbee.navigation

sealed class Screens(val route: String) {
    object FireSignInScreen : Screens(route = "Firebase_SignIn_Screen")
    object SplashScreen : Screens(route = "Splash_Screen")
    object FireSignUpScreen : Screens(route = "Firebase_SignUp_Screen")
    object HomeScreen : Screens(route = "Home_Screen")
    object CartScreen : Screens(route = "Cart_Screen")
    object Favourite : Screens(route = "Favourite_Screen")
    object Profile : Screens(route = "Profile_Screen")
    object Details : Screens(route = "Details_Screen") {
        fun passName(name: String): String {
            return "Details_Screen/$name"
        }
    }



    object Order : Screens(route = "Order_Screen")
    object PersonalDetails : Screens(route = "Personal_Details")
    object DeliveryAddress : Screens(route = "Delivery_Address")
    object PaymentMethod : Screens(route = "Payment_Method")
    object About : Screens(route = "About")
    object Help : Screens(route = "Help")
    object LogOut : Screens(route = "LogOut")


}