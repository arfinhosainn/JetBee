package com.example.jetbee.presentaion.cart_screen.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.jetbee.R
import com.example.jetbee.presentaion.common.RegularFont
import com.example.jetbee.ui.theme.LightBlack


@Composable
fun CartScreenHeader(title: String, navController: NavHostController) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = title,
                    color = Color.Black,
                    fontFamily = RegularFont,
                    fontWeight = FontWeight.Medium, fontSize = 25.sp
                )
            }
        },
        navigationIcon = {
            val radius = 18.dp
            val shape = RoundedCornerShape(radius)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .defaultMinSize(minWidth = radius * 2, minHeight = radius * 2)
                    .background(
                        color = Color.White,
                        shape = shape
                    )
                    .clip(shape),
            ) {
                Icon(
                    modifier = Modifier
                        .size(28.dp)
                        .clickable {
                            navController.popBackStack()
                        },
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "", tint = Color.Black
                )
            }
        },
        modifier = Modifier.padding(

           // horizontal = 15.dp
        ),
        backgroundColor = Color.White, elevation = 0.dp
    )
}


@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewCartScreenHeader() {

    CartScreenHeader(title = "Cart", navController = rememberNavController())

}