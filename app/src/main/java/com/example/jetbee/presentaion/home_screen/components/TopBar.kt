package com.example.jetbee.presentaion.home_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetbee.R
import com.example.jetbee.presentaion.common.RegularFont


@Composable
fun TopBar(onNavigationIconClick: () -> Unit) {
    val showDialog = remember {
        mutableStateOf(false)
    }
    if (showDialog.value) {
        DialogBox(showDialog = showDialog.value,
            dismissDialog = { showDialog.value = false })
    }
    TopAppBar(elevation = 0.dp,modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp), title = {
        Column {
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
                fontSize = 18.sp,
                style = TextStyle(color = Color.White)
            )
        }
    }, navigationIcon = {
        Image(painter = painterResource(id = R.drawable.coffee),
            contentDescription = "profile image",
            modifier = Modifier
                .size(58.dp)
                .clip(
                    CircleShape
                )
                .clickable {
                    onNavigationIconClick()
                })
    }, actions = {
        BadgedBox(
            badge = {
                Badge(
                    modifier = Modifier
                        .size(10.dp), backgroundColor = Color.Red
                )
            },
        ) {
            val radius = 20.dp
            val shape = RoundedCornerShape(radius)
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .defaultMinSize(minWidth = radius * 2, minHeight = radius * 2)
                    .background(
                        color = Color.White, shape = shape
                    )
                    .clip(shape),
            ) {
                Icon(
                    modifier = Modifier
                        .size(28.dp)
                        .clickable {
                            showDialog.value = true
                        },
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = "",
                    tint = Color.Black
                )
            }
        }

    }, backgroundColor = Color.Black)
}


@Composable
fun DialogBox(
    showDialog: Boolean,
    dismissDialog: () -> Unit
) {

    AlertDialog(onDismissRequest = { dismissDialog() }, title = {
        Column(
            modifier = Modifier
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Notification permission needed",
                maxLines = 2,
                fontFamily = RegularFont,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp, textAlign = TextAlign.Center
            )
            Text(
                text = "Go to Settings to allow Notification access",
                maxLines = 2,
                fontFamily = RegularFont,
                fontWeight = FontWeight.Medium,
                fontSize = 13.sp, textAlign = TextAlign.Center
            )
        }
    }, buttons = {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp), horizontalArrangement = Arrangement.End
        ) {
            Button(onClick = { dismissDialog() }) {
                Text(text = "Dismiss")
            }
            Spacer(modifier = Modifier.width(25.dp))
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Dismiss")
            }
        }
    })
}


@Preview(showBackground = true)
@Composable
fun PreviewHeaderItem() {
    TopBar(onNavigationIconClick = {})

}