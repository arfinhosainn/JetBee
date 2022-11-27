package com.example.jetbee.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.jetbee.presentaion.common.UserViewModel

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    onItemClick: (BottomNavItem) -> Unit,
     userViewModel: UserViewModel = hiltViewModel()
) {
    val backStafckEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(modifier = Modifier.clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)), backgroundColor = Color.Black) {
        items.forEach { item ->
            val selected = item.route == backStafckEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White,
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if (item.badgeCount > 0) {
                            BadgedBox(badge = {
                                Text(text = item.badgeCount.toString())
                            }) {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    contentDescription = "Item Icon"
                                )
                            }
                            userViewModel.updateUserData()
                        } else {
                            Icon(
                                painter = painterResource(id = item.icon),
                                contentDescription = item.name
                            )
                        }
                        if (selected) {
                            Text(text = item.name, textAlign = TextAlign.Center, fontSize = 10.sp)
                        }

                    }

                })

        }

    }

}