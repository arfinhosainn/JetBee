package com.example.jetbee.presentaion.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Badge
import androidx.compose.material.BadgedBox
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetbee.R
import com.example.jetbee.presentaion.common.RegularFont

@Composable
fun HeaderItem() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.coffee),
                contentDescription = "profile image",
                modifier = Modifier
                    .size(58.dp)
                    .clip(
                        CircleShape
                    )
            )
            Column(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    text = "Hi, Good Morning",
                    textAlign = TextAlign.Start,
                    fontFamily = RegularFont,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
                Text(
                    text = "Arfin Hosain",
                    textAlign = TextAlign.Start,
                    fontFamily = RegularFont,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )

            }

        }
        BadgedBox(
            badge = { Badge(modifier = Modifier.size(10.dp), backgroundColor = Color.Red) {} },
        ) {
            val radius = 20.dp
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
                    modifier = Modifier.size(28.dp),
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = "", tint = Color.Black
                )
            }
        }


    }

}

@Preview(showBackground = true)
@Composable
fun PreviewHeaderItem() {
    HeaderItem()

}