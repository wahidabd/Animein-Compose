package com.wahidabd.animeku.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.wahidabd.animeku.R
import com.wahidabd.animeku.domain.anime.model.Anime
import com.wahidabd.animeku.ui.component.anime.AnimeItem
import com.wahidabd.animeku.ui.component.Carousel
import com.wahidabd.animeku.ui.component.lottie.LottieEmpty
import com.wahidabd.animeku.ui.component.lottie.LottieError
import com.wahidabd.animeku.ui.component.lottie.LottieLoading
import com.wahidabd.animeku.ui.screen.destinations.AnimeDetailScreenDestination
import com.wahidabd.animeku.ui.screen.destinations.AnimeScreenDestination
import com.wahidabd.animeku.ui.theme.ColorPrimary
import com.wahidabd.animeku.utils.enums.AnimeType
import com.wahidabd.animeku.utils.rememberForeverLazyListState
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
    navigator: DestinationsNavigator?,
    viewModel: HomeViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorPrimary)
    ) {
        NestedScrollItem(navigator = navigator!!, viewModel = viewModel)
    }
}

@Composable
fun NestedScrollItem(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel
) {

    LaunchedEffect(Unit) {
        viewModel.initViewModel()
    }

    viewModel.spotLight.collectStateFlow(
        onLoading = { LottieLoading() },
        onFailure = { _, message ->
            LottieError(error = message.toString())
        },
        onEmpty = { LottieEmpty() },
    ) { animeList ->
        LazyColumn(
            state = rememberForeverLazyListState(key = "home"),
            contentPadding = PaddingValues(bottom = 16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item { TitleSection(type = AnimeType.SPOTLIGHT, navigator = navigator) }
            item { Carousel(animeList = animeList) }

            item { TitleSection(type = AnimeType.ONGOING, navigator = navigator) }
            item { HorizontalScrollableAnime(navigator = navigator, items = viewModel.ongoings) }

            item { TitleSection(type = AnimeType.POPULAR, navigator = navigator) }
            item { HorizontalScrollableAnime(navigator = navigator, items = viewModel.populars) }

            item { TitleSection(type = AnimeType.MOVIE, navigator = navigator) }
            item { HorizontalScrollableAnime(navigator = navigator, items = viewModel.movies) }

            item { TitleSection(type = AnimeType.FINISHED, navigator = navigator) }
            item { HorizontalScrollableAnime(navigator = navigator, items = viewModel.finished) }
        }
    }
}

@Composable
fun TitleSection(
    type: AnimeType,
    navigator: DestinationsNavigator? = EmptyDestinationsNavigator
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .clickable(
                enabled = type.query?.isNotBlank() == true,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color.LightGray)
            ) {
                navigator?.navigate(AnimeScreenDestination(type = type))
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = type.label,
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(all = 4.dp)
                .padding(top = 4.dp, bottom = 4.dp)
        )

        if (type.query?.isNotEmpty() == true) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                tint = Color.White,
                modifier = Modifier.size(24.dp),
                contentDescription = "Arrow"
            )
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
                state = rememberForeverLazyListState(key = "anime"),
                contentPadding = PaddingValues(horizontal = 8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(anime) { item ->
                    AnimeItem(
                        data = item,
                        onClick = {
                            navigator.navigate(AnimeDetailScreenDestination(slug = item.slug.toString()))
                        },
                    )
                }
            }
        }
    }
}