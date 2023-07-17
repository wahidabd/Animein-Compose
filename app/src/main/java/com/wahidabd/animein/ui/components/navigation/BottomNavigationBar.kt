package com.wahidabd.animein.ui.components.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.wahidabd.animein.ui.theme.ColorDarkPurple
import com.wahidabd.animein.ui.theme.ColorPrimary


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    navController: NavController,
    showBottomBar: Boolean = true,
    items: List<BottomNavItem> = listOf(
        BottomNavItem.Home,
        BottomNavItem.Schedule,
        BottomNavItem.Bookmark,
        BottomNavItem.More,
    ),
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavigation(
                    backgroundColor = ColorPrimary,
                    contentColor = ColorPrimary,
                    elevation = 4.dp
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentItem = navBackStackEntry?.destination

                    items.forEach { item ->
                        val selected = currentItem?.route == item.destination.route
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    painter = painterResource(id = item.icon),
                                    tint = if (selected) Color.White else Color.LightGray,
                                    contentDescription = item.title
                                )
                            },
                            label = {
                                Text(
                                    text = item.title,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = if (selected) Color.White else Color.LightGray
                                )
                            },
                            selectedContentColor = ColorDarkPurple,
                            unselectedContentColor = Color.LightGray,
                            alwaysShowLabel = false,
                            selected = currentItem?.route?.contains(item.destination.route) == true,
                            onClick = {
                                navController.navigate(item.destination.route){
                                    navController.graph.startDestinationRoute?.let { route ->
                                        popUpTo(route){
                                            saveState = true
                                        }
                                    }
//                                    popUpTo(navController.graph.findStartDestination().id){
//                                        saveState = true
//                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}