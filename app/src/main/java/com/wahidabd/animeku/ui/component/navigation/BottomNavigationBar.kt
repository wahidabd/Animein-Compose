package com.wahidabd.animeku.ui.component.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.wahidabd.animeku.ui.theme.ColorPrimary


/**
 * Created by wahid on 11/15/2023.
 * Github github.com/wahidabd.
 */


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar(
    navController: NavController,
    showBottomBar: Boolean = true,
    items: List<NavigationItem> = listOf(
        NavigationItem.Home,
        NavigationItem.Search,
        NavigationItem.Bookmark,
        NavigationItem.More
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
                            selected = currentItem?.route?.contains(item.destination.route) == true,
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
                            selectedContentColor = ColorPrimary,
                            unselectedContentColor = Color.LightGray,
                            alwaysShowLabel = false,
                            onClick = {
                                navController.navigate(item.destination.route) {

                                    if (currentItem != null) {
                                        navController.graph.startDestinationRoute?.let { route ->
                                            popUpTo(route) {
                                                saveState = true
                                                inclusive = true
                                            }
                                        }
                                    } else {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                            inclusive = true
                                        }
                                    }
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