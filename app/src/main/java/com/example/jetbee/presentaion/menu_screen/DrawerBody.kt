package com.example.jetbee.presentaion.menu_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerBody(
    items: List<MenuItem>,
    modifier: Modifier = Modifier,
    itemTextStyle: TextStyle = TextStyle(fontSize = 18.sp),
    onItemClick: (MenuItem) -> Unit
) {
    LazyColumn(modifier) {
        items(items) { item ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .clickable { onItemClick(item) }
                .padding(16.dp))
            {
                Icon(imageVector = item.icon, contentDescription = item.contentDescription)
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = item.title, style = itemTextStyle, modifier = Modifier.weight(1f))
            }

        }
    }


}