package com.wahidabd.animein.ui.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wahidabd.animein.ui.screen.destinations.HomeScreenDestination
import com.wahidabd.animein.ui.theme.ColorPrimary
import kotlinx.coroutines.delay


/**
 * Created by Wahid on 7/7/2023.
 * Github github.com/wahidabd.
 */


@RootNavGraph(start = true)
@Destination
@Composable
fun SplashScreen(
    navigator: DestinationsNavigator
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(ColorPrimary)
    ) {
        Box(contentAlignment = Alignment.Center){
            LaunchedEffect(Unit){
                delay(1000)
                navigator.popBackStack()
                navigator.navigate(HomeScreenDestination)
            }
        }
    }
}