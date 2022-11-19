package com.example.jetbee.presentaion.detail_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.jetbee.domain.model.Product
import com.example.jetbee.ui.theme.LightBlack

@Composable
fun DetailScreen(product: Product) {
    //val state = detailViewModel.product.value

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                .background(color = LightBlack)
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = product.image ),
                contentDescription = "detail Image",
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(200.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = product.name)
            Text(text = "$20.33")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, top = 5.dp),
        ) {
            Text(text = "♥♥♥♥♥♥")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "jfskdjgksjgkasjgkasjgkjaskgjsakjgksajg" +
                    "jsdifjskd" +
                    "fjsdkfjskfj" +
                    "fjsjfgkasjgksjkgjskgjsgksgksgksgsskskgjksjgjsksk"
        )
    }
}

/*

@Preview(showBackground = true)
@Composable
fun PreviewDetailScreen() {
    DetailScreen()

}*/
