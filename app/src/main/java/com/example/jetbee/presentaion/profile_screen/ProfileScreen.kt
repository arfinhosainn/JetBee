package com.example.jetbee.presentaion.profile_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetbee.R
import com.example.jetbee.navigation.Screens
import com.example.jetbee.presentaion.cart_screen.components.AppTopBar
import com.example.jetbee.presentaion.common.RegularFont

@Composable
fun ProfileScreen(
    navController: NavController,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {

    val context = LocalContext.current

    Scaffold(topBar = {
        AppTopBar(
            title = "Profile ",
            navController = navController,
            backGroundColor = Color.Black,
            titleColor = Color.White, navIcon = painterResource(id = R.drawable.back_icon)
        )
    }, content = { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(80.dp),
                    painter = painterResource(id = R.drawable.arfin),
                    contentDescription = "Profile Image", contentScale = ContentScale.Crop
                )
                Text(
                    text = "Arfin Hosain",
                    fontFamily = RegularFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    style = TextStyle(color = Color.White)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            ListOfItem(
                items = listOf(
                    ProfileMenuItem(
                        name = "Order",
                        icon = R.drawable.delivery,
                        route = Screens.Order.route
                    ),
                    ProfileMenuItem(
                        name = "Personal Details",
                        icon = R.drawable.profile,
                        route = Screens.PersonalDetails.route
                    ),
                    ProfileMenuItem(
                        name = "Delivery Address",
                        icon = R.drawable.delivery,
                        route = Screens.DeliveryAddress.route
                    ),
                    ProfileMenuItem(
                        name = "Payment Method",
                        icon = R.drawable.creditcard,
                        route = Screens.PaymentMethod.route
                    ),
                    ProfileMenuItem(
                        name = "About",
                        icon = R.drawable.about,
                        route = Screens.About.route
                    ),
                    ProfileMenuItem(
                        name = "Help",
                        icon = R.drawable.help,
                        route = Screens.Help.route
                    ),
                    ProfileMenuItem(
                        name = "Log Out",
                        icon = R.drawable.logout,
                        route = Screens.LogOut.route
                    )
                ),
                onMenuItemClick = {
                    when (it.route) {
                        "LogOut" -> {
                            profileViewModel.signOut()
                            navController.navigate(Screens.FireSignInScreen.route){
                                popUpTo(route = Screens.HomeScreen.route) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                    //Toast.makeText(context, it.route, Toast.LENGTH_SHORT).show()
                }
            )
        }
    })
}


@Composable
fun ListOfItem(
    items: List<ProfileMenuItem>,
    onMenuItemClick: (ProfileMenuItem) -> Unit,
    modifier: Modifier = Modifier
) {
    items.forEach { item ->
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp), shape = RoundedCornerShape(15.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clickable {
                        onMenuItemClick(item)
                    }
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp, top = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Image(painter = painterResource(id = item.icon), contentDescription = "Item Icon")
                Spacer(modifier = Modifier.width(20.dp))
                Text(
                    text = item.name,
                    fontFamily = RegularFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                    style = TextStyle(color = Color.White)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.End
                ) {
                    item.actionIcon?.let { painterResource(id = it) }?.let {
                        Image(
                            painter = it,
                            contentDescription = "Action Icon"
                        )
                    }
                }
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun ProfileMenu(
    modifier: Modifier = Modifier
) {

    ListOfItem(items =
    listOf(
        ProfileMenuItem(
            name = "Order",
            icon = R.drawable.cart,
            route = Screens.Order.route
        ),
        ProfileMenuItem(
            name = "Personal Details",
            icon = R.drawable.cart,
            route = Screens.PersonalDetails.route
        ),
        ProfileMenuItem(
            name = "Delivery Address",
            icon = R.drawable.cart,
            route = Screens.DeliveryAddress.route
        ),
        ProfileMenuItem(
            name = "Payment Method",
            icon = R.drawable.cart,
            route = Screens.PaymentMethod.route
        ),
        ProfileMenuItem(
            name = "About",
            icon = R.drawable.cart, route = Screens.About.route
        ),
        ProfileMenuItem(
            name = "Help",
            icon = R.drawable.cart,
            route = Screens.Help.route
        ),
        ProfileMenuItem(
            name = "Log Out",
            icon = R.drawable.cart,
            route = Screens.LogOut.route
        )
    ),
        onMenuItemClick = {})
}