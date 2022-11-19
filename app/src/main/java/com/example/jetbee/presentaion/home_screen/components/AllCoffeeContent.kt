package com.example.jetbee.presentaion.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.jetbee.domain.model.CartProducts
import com.example.jetbee.domain.model.Product
import com.example.jetbee.presentaion.cart_screen.CoffeeCartViewModel
import com.example.jetbee.presentaion.common.RegularFont
import com.example.jetbee.presentaion.common.UserViewModel
import com.example.jetbee.presentaion.destinations.MainDetailScreenDestination
import com.example.jetbee.presentaion.detail_screen.ProductDetailViewModel
import com.example.jetbee.ui.theme.LightBlack
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun AllCoffeeContent(
    product: List<Product>,
    navController : NavHostController,
    navigator: DestinationsNavigator
    ) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(130.dp),
        content = {
            items(product.size) { i ->
                CoffeeCard( product = product[i], navigator = navigator)
            }
        })

}


@Composable
fun CoffeeCard(
    product: Product,
    userViewModel: UserViewModel = hiltViewModel(),
    coffeeCartViewModel: CoffeeCartViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
    detailViewModel: ProductDetailViewModel = hiltViewModel()

    ) {
    val state  = detailViewModel.product.value

    Card(
        modifier = Modifier
            .height(270.dp)
            .padding(6.dp)
            .clickable {
                detailViewModel.setProduct(product)
                navigator.navigate(
                    MainDetailScreenDestination(product.name, product = product  )
                )
            }
            .width(213.dp), backgroundColor = if (isSystemInDarkTheme()) LightBlack else Color.Red,
        shape = RoundedCornerShape(25.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp, end = 15.dp, top = 20.dp)
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(100.dp)
                    .width(100.dp),
                painter = rememberAsyncImagePainter(
                    model = product.image,
                    contentScale = ContentScale.Crop
                ),
                contentDescription = "Coffee"
            )
            Text(
                modifier = Modifier.padding(top = 28.dp),
                text = product.name,
                fontFamily = RegularFont,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp, color = Color.White,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Text(
                modifier = Modifier.padding(top = 3.dp),
                text = product.tagline,
                fontFamily = RegularFont,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp, color = Color.LightGray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$${product.price}",
                    fontFamily = RegularFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp, color = Color.White
                )
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            addTOCard(
                                userViewModel = userViewModel,
                                coffeeCartViewModel = coffeeCartViewModel,
                                product = product
                            )

                        }
                        .clip(shape = CircleShape)
                        .background(Color.White), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        imageVector = Icons.Default.Add,
                        contentDescription = "Filter Icon", tint = Color.Black
                    )

                }
            }
        }
    }
}


fun addTOCard(
    userViewModel: UserViewModel,
    coffeeCartViewModel: CoffeeCartViewModel,
    product: Product
) {
    val doesProductAlreadyExits =
        userViewModel.userState.value.cartProducts.find {
            it.productName == product.name
        }
    CoroutineScope(Dispatchers.IO).launch {
        if (doesProductAlreadyExits == null) {
            coffeeCartViewModel.addCoffeeToCart(
                cartProduct = CartProducts(
                    productName = product.name,
                    productPrice = product.price,
                    productImageUrls = product.image,
                )
            )
            userViewModel.updateUserData()
        }

    }

}


/*
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun Previewcard() {
    CoffeeCard(product = Product())

}*/
