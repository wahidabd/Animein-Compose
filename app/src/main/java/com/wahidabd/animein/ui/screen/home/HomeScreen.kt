package com.wahidabd.animein.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.wahidabd.animein.ui.components.home.AnimeItem
import com.wahidabd.animein.ui.components.home.ProfileSearch
import com.wahidabd.animein.ui.theme.ColorPrimary
import org.koin.androidx.compose.koinViewModel

/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */

@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = koinViewModel()
) {


    val anime = viewModel.anime.value.collectAsLazyPagingItems()
    val listState: LazyListState = rememberLazyListState()

    Column(
        modifier = Modifier
            .background(ColorPrimary)
            .fillMaxSize()
    ) {
        ProfileSearch(navigator = navigator)

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            state = listState
        ) {

            items(anime) {
                AnimeItem(data = it!!) {}
            }

            when (anime.loadState.refresh) {
                is LoadState.Loading -> {

                }
                is LoadState.Error -> {}
                else -> {}
            }
        }

//        LazyColumn(
//            state = listState
//        ) {
//            item {
//                viewModel.carousel.collectStateFlow(
//                    onLoading = {
//                        debug { "ON LOADING" }
//                    },
//                    onFailure = { _, m ->
//                        showSnackbarMessage(LocalView.current, m.toString())
//                    },
//                    onEmpty = {
//                        debug { "ON EMPTY" }
//                    },
//                    onSuccess = {
//                        debug { "HOME -> $it" }
//                        CarouselHome(carousel = it)
//                    }
//                )
//            }
//            lazyPagingAnime(items = anime, navigator = navigator)
//        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(EmptyDestinationsNavigator)
}