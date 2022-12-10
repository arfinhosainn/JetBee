package com.example.jetbee.presentaion.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.jetbee.R
import com.example.jetbee.navigation.Screens
import com.example.jetbee.presentaion.common.RegularFont
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    splashScreenViewModel: SplashScreenViewModel = hiltViewModel(),
    navController: NavController
) {

    val onboarding by splashScreenViewModel.onBoardingCompleted.collectAsState()

    LaunchedEffect(key1 = true) {
        delay(1000L)
        navController.popBackStack()
        if (onboarding) {
            navController.navigate(Screens.HomeScreen.route)
        } else {
            navController.navigate(Screens.FireSignInScreen.route)
        }
    }
    Splash()
}

@Composable
fun Splash() {

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.splash), modifier = Modifier.fillMaxSize(),
            contentDescription = "Splash Image", contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(start = 20.dp)
                .background(
                    Brush.verticalGradient(
                        0F to Color.Transparent,
                        .1F to Color.Black.copy(alpha = 0.5F),
                        1F to Color.Black.copy(alpha = 0.8F)
                    )
                )
        ) {
            Text(
                modifier = Modifier.padding(bottom = 40.dp),
                text = "Enjoy the taste of\nour best coffee",
                color = Color.White,
                fontSize = 30.sp,
                style = MaterialTheme.typography.body2,
                fontFamily = com.example.jetbee.presentaion.common.SplashScreen
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier
                        .height(60.dp)
                        .width(250.dp),
                    onClick = { /*TODO*/ },
                    shape = RoundedCornerShape(40.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = "Get Started",
                        fontSize = 17.sp,
                        fontFamily = RegularFont,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSplashScreen() {
    Splash()

}