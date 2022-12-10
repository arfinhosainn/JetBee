package com.example.jetbee.presentaion.detail_screen

import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.jetbee.R
import com.example.jetbee.domain.model.CartProducts
import com.example.jetbee.domain.model.Product
import com.example.jetbee.presentaion.cart_screen.CoffeeCartViewModel
import com.example.jetbee.presentaion.cart_screen.components.AppTopBar
import com.example.jetbee.presentaion.common.RegularFont
import com.example.jetbee.presentaion.common.UserViewModel
import com.example.jetbee.ui.theme.LightBlack
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailScreen(
    product: Product, navController: NavController,
    userViewModel: UserViewModel = hiltViewModel(),
    coffeeCartViewModel: CoffeeCartViewModel = hiltViewModel(),
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(topBar = {
        AppTopBar(
            title = "Detail Item",
            navController = navController,
            backGroundColor = LightBlack,
            titleColor = Color.White
        )
    }, content = { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize().padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp)
                    .background(LightBlack),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    modifier = Modifier.size(200.dp),
                    painter = rememberAsyncImagePainter(model = product.image),
                    contentDescription = "Product Image"
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color.Black)
                    .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, end = 25.dp, top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.weight(4f),
                        text = product.name,
                        fontFamily = RegularFont,
                        fontWeight = FontWeight.Medium,
                        fontSize = 20.sp,
                        maxLines = 2,
                        style = TextStyle(color = Color.White)
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = "Rating",
                        fontFamily = RegularFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 20.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, end = 25.dp),
                ) {
                    Text(
                        text = product.category,
                        fontSize = 18.sp,
                        fontFamily = RegularFont,
                        fontWeight = FontWeight.Normal, style = TextStyle(color = Color.Gray)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, end = 25.dp, top = 20.dp),
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Start),
                        text = "Choice of Milk",
                        fontSize = 18.sp,
                        fontFamily = RegularFont,
                        fontWeight = FontWeight.Normal, style = TextStyle(color = Color.Black)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        categoryList.forEach { milk ->
                            Chip(
                                onClick = { /*TODO*/ }, colors = ChipDefaults.chipColors(
                                    backgroundColor = Color.Black, contentColor = Color.White
                                )
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.cup),
                                    contentDescription = "Cup Icon", modifier = Modifier.size(25.dp)
                                )
                                Text(
                                    text = milk,
                                    fontSize = 12.sp,
                                    fontFamily = RegularFont,
                                    fontWeight = FontWeight.Medium,
                                )
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp), horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedButton(
                        modifier = Modifier.size(35.dp),
                        onClick = { /*TODO*/ },
                        shape = CircleShape,
                        contentPadding = PaddingValues(5.dp),
                        border = BorderStroke(2.dp, color = Color.Gray),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "content description",
                            tint = Color.Gray
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(
                        text = "1",
                        fontSize = 20.sp,
                        fontFamily = RegularFont,
                        fontWeight = FontWeight.Normal, style = TextStyle(color = Color.Black)
                    )
                    Spacer(modifier = Modifier.width(20.dp))

                    OutlinedButton(
                        modifier = Modifier.size(35.dp),
                        onClick = {
                        },
                        shape = CircleShape,
                        contentPadding = PaddingValues(5.dp),
                        border = BorderStroke(2.dp, color = Color.Gray),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Blue)
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "content description",
                            tint = Color.Gray
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, end = 25.dp, top = 25.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Total Price:",
                            fontSize = 18.sp,
                            fontFamily = RegularFont,
                            fontWeight = FontWeight.Medium, style = TextStyle(color = Color.Gray)
                        )
                        Text(
                            text = "$ ${product.price}",
                            fontSize = 25.sp,
                            fontFamily = RegularFont,
                            fontWeight = FontWeight.Bold, style = TextStyle(color = Color.White)
                        )
                    }

                    Button(
                        modifier = Modifier
                            .height(60.dp)
                            .width(200.dp),
                        onClick = {
                            addToCart(
                                userViewModel = userViewModel,
                                coffeeCartViewModel = coffeeCartViewModel,
                                product = product
                            )
                            scope.launch {
                                Toast.makeText(
                                    context,
                                    "${product.name} Added Successfully",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }

                        },
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White,
                            contentColor = Color.Black,
                        )

                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp), horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.cart),
                                contentDescription = "Button Cart Icon"
                            )
                            Text(
                                text = "Add to cart",
                                fontFamily = RegularFont
                            )
                        }
                    }
                }
            }
        }
    }, backgroundColor = Color.Black)
}


fun addToCart(
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


val categoryList = listOf<String>("Soy Milk", "Cow Milk", "Camel Milk")


@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    DetailScreen(
        product = Product(),
        navController = rememberNavController()
    )

}
