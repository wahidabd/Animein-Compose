package com.wahidabd.animein.ui.components.home

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wahidabd.animein.data.anime.model.AnimeResponse


/**
 * Created by Wahid on 7/15/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun AnimeLoadStateHome(
    navigator: DestinationsNavigator,
    pagingItems: LazyPagingItems<AnimeResponse>,
    onErrorClick: () -> Unit
) {
    when(pagingItems.loadState.refresh){
        is LoadState.Loading -> {}
        is LoadState.NotLoading -> {}
        is LoadState.Error -> {}
    }
}