package com.wahidabd.animeku.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wahidabd.animeku.domain.anime.model.Anime
import com.wahidabd.animeku.ui.component.AnimeItem
import com.wahidabd.animeku.ui.component.Carousel
import com.wahidabd.animeku.ui.component.TitleSection
import com.wahidabd.animeku.ui.component.lottie.LottieEmpty
import com.wahidabd.animeku.ui.component.lottie.LottieError
import com.wahidabd.animeku.ui.component.lottie.LottieLoading
import com.wahidabd.animeku.ui.theme.ColorPrimary
import com.wahidabd.animeku.utils.enums.AnimeType
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.exts.collectStateFlow
import kotlinx.coroutines.flow.StateFlow


/**
 * Created by wahid on 11/15/2023.
 * Github github.com/wahidabd.
 */


@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorPrimary)
    ) {
        NestedScrollItem(navigator = navigator)
    }
}

@Composable
fun NestedScrollItem(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.initViewModel()
    }

    val listState: LazyListState = rememberLazyListState()
    viewModel.spotLight.collectStateFlow(
        onLoading = { LottieLoading() },
        onFailure = { _, message ->
            LottieError(error = message.toString())
        },
        onEmpty = { LottieEmpty() },
    ) { animes ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item { TitleSection(type = AnimeType.SPOTLIGHT) }
            item { Carousel(animeList = animes) }

            item { TitleSection(type = AnimeType.ONGOING) }
            item { HorizontalScrollableAnime(navigator = navigator, items = viewModel.ongoings) }

            item { TitleSection(type = AnimeType.POPULAR) }
            item { HorizontalScrollableAnime(navigator = navigator, items = viewModel.populars) }

            item { TitleSection(type = AnimeType.MOVIE) }
            item { HorizontalScrollableAnime(navigator = navigator, items = viewModel.movies) }

            item { TitleSection(type = AnimeType.FINISHED) }
            item { HorizontalScrollableAnime(navigator = navigator, items = viewModel.finished) }
        }
    }
}

@Composable
fun HorizontalScrollableAnime(
    navigator: DestinationsNavigator,
    items: StateFlow<Resource<List<Anime>>>
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        items.collectStateFlow(
            onLoading = {},
            onFailure = { _, _ -> }
        ) { anime ->
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(anime) { item ->
                    AnimeItem(data = item, onClick = {})
                }
            }
        }
    }
}