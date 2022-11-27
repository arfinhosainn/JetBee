package com.example.jetbee.presentaion.cart_screen.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetbee.presentaion.common.RegularFont
import com.example.jetbee.presentaion.common.UserViewModel
import com.example.jetbee.ui.theme.LightGray100
import kotlin.math.roundToInt

@Composable
fun CartBillItem(modifier: Modifier = Modifier, userViewModel: UserViewModel = hiltViewModel()) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        elevation = 20.dp,
        shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp),
        backgroundColor = Color.White
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Text(
                        text = "Total Price",
                        fontSize = 20.sp,
                        fontFamily = RegularFont,
                        style = TextStyle(color = Color.Black)
                    )
                    Text(
                        text = "$${calculateTotalPrice(userViewModel)}",
                        fontFamily = RegularFont,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Medium,
                        style = TextStyle(color = Color.Black)
                    )
                }
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .height(55.dp),
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.Black, contentColor = Color.White
                    )
                ) {
                    Text(
                        "Check Out",
                        fontFamily = RegularFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 18.sp
                    )
                }

            }

        }
    }
}


private fun calculateTotalPrice(userViewModel: UserViewModel): Float {
    val price = userViewModel.userState.value.cartProducts.map {
        it.productPrice.toFloat()
    }
    return price.sum()
}


private data class DottedShape(
    val step: Dp,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(Path().apply {
        val stepPx = with(density) { step.toPx() }
        val stepsCount = (size.width / stepPx).roundToInt()
        val actualStep = size.width / stepsCount
        val dotSize = Size(width = actualStep / 2, height = size.height)
        for (i in 0 until stepsCount) {
            addRect(
                Rect(
                    offset = Offset(x = i * actualStep, y = 0f),
                    size = dotSize
                )
            )
        }
        close()
    })
}


