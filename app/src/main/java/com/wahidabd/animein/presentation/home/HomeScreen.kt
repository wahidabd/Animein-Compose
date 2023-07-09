package com.wahidabd.animein.presentation.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */

@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator
) {

}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(EmptyDestinationsNavigator)
}