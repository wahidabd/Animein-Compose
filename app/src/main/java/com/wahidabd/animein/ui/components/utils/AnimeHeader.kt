package com.wahidabd.animein.ui.components.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator


/**
 * Created by Wahid on 7/16/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun AnimeHeader(
    navigator: DestinationsNavigator,
    title: String
) {
    Row(
        modifier = Modifier
    ) {

    }
}

@Preview
@Composable
fun AnimeHeaderPreview() {
    AnimeHeader(navigator = EmptyDestinationsNavigator, title = "Anime")
}