package com.wahidabd.animeku.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine
import com.wahidabd.animeku.ui.component.navigation.BottomNavigationBar
import com.wahidabd.animeku.ui.screen.destinations.BookmarkScreenDestination
import com.wahidabd.animeku.ui.screen.destinations.HomeScreenDestination
import com.wahidabd.animeku.ui.screen.destinations.MoreScreenDestination
import com.wahidabd.animeku.ui.screen.destinations.SearchScreenDestination
import com.wahidabd.animeku.ui.theme.AnimekuTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimekuTheme {
                MainScreen()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen() {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        val navController = rememberAnimatedNavController()
        val navHostEngine = rememberNavHostEngine()

        val newsBackStackEntry by navController.currentBackStackEntryAsState()
        val route = newsBackStackEntry?.destination?.route

        BottomNavigationBar(
            navController = navController,
            showBottomBar = route in listOf(
                HomeScreenDestination.route,
                SearchScreenDestination.route,
                BookmarkScreenDestination.route,
                MoreScreenDestination.route
            )
        ) { paddingValues ->
            Box(
                modifier = Modifier.padding(paddingValues)
            ) {
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    navController = navController,
                    engine = navHostEngine
                )
            }
        }
    }
}

@Preview
@Composable
fun MainPreview() {
    MainScreen()
}