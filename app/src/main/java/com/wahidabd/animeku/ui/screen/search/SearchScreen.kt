package com.wahidabd.animeku.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.wahidabd.animeku.ui.component.SearchBar
import com.wahidabd.animeku.ui.theme.ColorPrimary


/**
 * Created by wahid on 11/15/2023.
 * Github github.com/wahidabd.
 */


@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator
) {
    Column(
        modifier = Modifier
            .background(ColorPrimary)
            .fillMaxSize()
    ) {
        SearchBar(
            autoFocus = true,
            onSearch = {  },

        )
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        navigator = EmptyDestinationsNavigator
    )
}