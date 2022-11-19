package com.example.jetbee.presentaion.cart_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.jetbee.presentaion.common.RegularFont
import com.example.jetbee.presentaion.common.UserViewModel
import com.example.jetbee.ui.theme.DarkYellow
import com.example.jetbee.ui.theme.LightGray100
import java.math.RoundingMode
import kotlin.math.roundToInt

@Composable
fun CartBillItem(modifier: Modifier = Modifier, userViewModel: UserViewModel = hiltViewModel()) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(190.dp),
        elevation = 20.dp,
        shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp),
        backgroundColor = LightGray100
    ) {
        Column(
            modifier = Modifier
                .padding(top = 15.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Bottom),
                    text = "Delivery",
                    fontFamily = RegularFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp, color = Color.White
                )
                Box(
                    Modifier
                        .height(2.dp)
                        .width(200.dp)
                        .padding(start = 10.dp)
                        .background(Color.Gray, shape = DottedShape(step = 10.dp)),
                )

                Text(
                    modifier = Modifier
                        .padding(start = 20.dp),
                    text = "$5.99",
                    fontFamily = RegularFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp, color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Bottom),
                    text = "Coffee",
                    fontFamily = RegularFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp, color = Color.White
                )
                Box(
                    Modifier
                        .height(2.dp)
                        .width(210.dp)
                        .padding(start = 10.dp)
                        .background(Color.Gray, shape = DottedShape(step = 10.dp)),
                )

                Text(
                    modifier = Modifier
                        .padding(start = 20.dp),
                    text = "$${calculateTotalPrice(userViewModel)}",
                    fontFamily = RegularFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 17.sp, color  = Color.White
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 25.dp, end = 25.dp, bottom = 15.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Divider(color = Color.LightGray)

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = DarkYellow, contentColor = Color.Black
                )
            ) {
                Text(
                    "Pay $${calculateTotalPrice(userViewModel).toBigDecimal().setScale(2, RoundingMode.HALF_EVEN)}",
                    fontFamily = RegularFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
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


