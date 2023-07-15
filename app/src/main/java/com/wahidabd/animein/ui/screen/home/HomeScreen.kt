package com.wahidabd.animein.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.wahidabd.animein.ui.components.home.AnimeItem
import com.wahidabd.animein.ui.components.home.ProfileSearch
import com.wahidabd.animein.ui.theme.ColorPrimary

/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */

@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator?,
    viewModel: HomeViewModel = hiltViewModel(),
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorPrimary)
    ) {
        ProfileSearch(navigator = navigator!!)
        ScrollItemHome(navigator = navigator, viewModel = viewModel)
    }
}

@Composable
fun ScrollItemHome(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel
) {

    val anime = viewModel.anime.value.collectAsLazyPagingItems()

    val listState: LazyListState = rememberLazyListState()
    LazyColumn(
        state = listState,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        items(anime) { item ->
            AnimeItem(data = item) {}
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(EmptyDestinationsNavigator)
}