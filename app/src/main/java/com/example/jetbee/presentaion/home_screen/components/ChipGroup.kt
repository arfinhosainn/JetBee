package com.example.jetbee.presentaion.home_screen.components

import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipGroup() {

    Chip(onClick = { /*TODO*/ }, leadingIcon = {
        Icon(Icons.Default.Call, contentDescription = "")
    }) {
        Text(text = "Cappuccino")

    }


}


@Preview
@Composable
fun PreviewChipGroup() {
    ChipGroup()
}