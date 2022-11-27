package com.example.jetbee.presentaion.cart_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.jetbee.R
import com.example.jetbee.domain.model.CartProducts
import com.example.jetbee.presentaion.common.RegularFont
import com.example.jetbee.presentaion.common.UserViewModel
import com.example.jetbee.ui.theme.LightBlack

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CartBodyItem(
    cartProduct: CartProducts,
    userViewModel: UserViewModel = hiltViewModel(),

    ) {

    val dismissState = rememberDismissState(confirmStateChange = {
        if (it == DismissValue.DismissedToStart) {
            userViewModel.deleteCoffeeFromCart(cartProducts = cartProduct)
            userViewModel.updateUserData()
        }
        true
    })

    SwipeToDismiss(
        state = dismissState,
        dismissThresholds = { FractionalThreshold(0.5f) },
        directions = setOf(DismissDirection.EndToStart),
        dismissContent = {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
                    .clickable {

                    }
                    .height(100.dp), backgroundColor = LightBlack,
                shape = RoundedCornerShape(25.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = cartProduct.productImageUrls),
                        contentDescription = "Cart Product Image",
                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(25.dp)
                            )
                            .size(80.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp, top = 5.dp),
                        horizontalAlignment = Alignment.End,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            modifier = Modifier.clickable {
                            },
                            text = cartProduct.productName,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = RegularFont,
                            fontWeight = FontWeight.Normal, color = Color.White,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "$${cartProduct.productPrice}",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontFamily = RegularFont,
                            fontWeight = FontWeight.Medium, color = Color.White,
                            fontSize = 20.sp
                        )
                    }
                }
            }
        },
        background = {

            Row(
                modifier = Modifier.fillMaxWidth().fillMaxSize().padding(end = 10.dp),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painterResource(id = R.drawable.delete),
                    modifier = Modifier
                        .size(25.dp)
                        .align(Alignment.CenterVertically),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }

    )

}


/*
@Preview(showBackground = true)
@Composable
fun PreviewCartBodyItem() {
    CartBodyItem()

}*/
