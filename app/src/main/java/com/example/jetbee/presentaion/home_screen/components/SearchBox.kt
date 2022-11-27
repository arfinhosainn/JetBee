package com.example.jetbee.presentaion.home_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetbee.R
import com.example.jetbee.ui.theme.LightMagenta


@Composable
fun SearchBox() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        TextField(modifier = Modifier
            .fillMaxWidth()
            .height(55.dp), value = "",
            onValueChange = {
                it
            },
            shape = RoundedCornerShape(30.dp),
            placeholder = {
                Text(
                    text = "Search Coffee...",
                    color = Color.LightGray,
                    fontSize = 13.sp
                )
            },
            singleLine = true,
            trailingIcon = {
                Box(
                    modifier = Modifier
                        .size(30.dp)
                        .clip(shape = CircleShape)
                        .background(Color.Black), contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.filter),
                        contentDescription = "Filter Icon", tint = Color.White
                    )

                }
            }, colors = TextFieldDefaults.textFieldColors(
                backgroundColor = if (isSystemInDarkTheme()) Color.White else Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = "Search Icon", tint = LightMagenta
                )
            }
        )


    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchBox() {
    SearchBox()

}