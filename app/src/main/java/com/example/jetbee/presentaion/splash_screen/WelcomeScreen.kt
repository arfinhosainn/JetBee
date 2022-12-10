package com.example.jetbee.presentaion.splash_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.jetbee.presentaion.common.RegularFont
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun WelcomeScreen(
    navController: NavController
) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Third
    )
    val pagerState = rememberPagerState()

    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            modifier = Modifier.weight(10f),
            count = 3,
            state = pagerState,
            verticalAlignment = Alignment.Top
        ) { position ->
            PagerScreen(onBoardingPage = pages[position])
        }
        HorizontalPagerIndicator(
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterHorizontally),
            pagerState = pagerState,
            activeColor = Color.Green,
            inactiveColor = Color.Gray,
            indicatorWidth = 10.dp,
            spacing = 5.dp
        )
    }

}


@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = "OnBoarding Image",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f), contentScale = ContentScale.Crop
        )
        Text(
            text = onBoardingPage.title,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .padding(top = 15.dp),
            fontFamily = RegularFont,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.h5.fontSize
        )
        Text(
            text = onBoardingPage.description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 15.dp),
            fontFamily = RegularFont,
            color = Color.Gray,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        )
    }
}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerButton(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    onClick: () -> Unit
) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = onClick, colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Green, contentColor = Color.White
            )
        ) {
            Text(text = "")

        }

    }


}