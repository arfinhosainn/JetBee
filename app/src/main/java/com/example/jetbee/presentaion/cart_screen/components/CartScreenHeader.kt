package com.example.jetbee.presentaion.cart_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetbee.presentaion.common.RegularFont


@Composable
fun AppTopBar(
    title: String,
    navController: NavController,
    navIcon: Painter? = null,
    actionIcon: Painter? = null,
    backGroundColor: Color,
    titleColor: Color
) {
    TopAppBar(
        title = {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = title,
                    color = titleColor,
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
                if (navIcon != null) {
                    Icon(
                        modifier = Modifier
                            .size(28.dp)
                            .clickable {
                                navController.popBackStack()
                            },
                        painter = navIcon,
                        contentDescription = "", tint = Color.Black
                    )
                }
            }
        },
        modifier = Modifier.padding(

            // horizontal = 15.dp
        ),
        backgroundColor =backGroundColor, elevation = 0.dp
    )
}

/*

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewCartScreenHeader() {

    CartScreenHeader(title = "Cart", navController = rememberNavController())

}*/
