package com.wahidabd.animein.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.wahidabd.animein.screen.destinations.AnimeScreenDestination
import com.wahidabd.animein.screen.destinations.SearchScreenDestination
import com.wahidabd.animein.ui.components.anime.CarouselHome
import com.wahidabd.animein.ui.components.anime.ProfileSearch
import com.wahidabd.animein.ui.components.anime.ScrollableAnimeItem
import com.wahidabd.animein.ui.components.lottie.LottieEmpty
import com.wahidabd.animein.ui.components.lottie.LottieError
import com.wahidabd.animein.ui.components.lottie.LottieLoading
import com.wahidabd.animein.ui.theme.ColorPrimary
import com.wahidabd.animein.utils.collectStateFlow
import com.wahidabd.animein.utils.enums.AnimeType
import com.wahidabd.animein.utils.navArgs.AnimePagingArgs

/**
 * Created by Wahid on 7/9/2023.
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
        ProfileSearch(navigator = navigator!!)
        NestedScrollItemHome(navigator = navigator, viewModel = viewModel)
    }
}

@Composable
fun NestedScrollItemHome(
    navigator: DestinationsNavigator,
    viewModel: HomeViewModel
) {

    LaunchedEffect(Unit) {
        viewModel.initViewModel()
    }

    val listState: LazyListState = rememberLazyListState()
    viewModel.carousel.collectStateFlow(
        onLoading = {
            LottieLoading()
        },
        onFailure = { _, message ->
            LottieError(message)
        },
        onEmpty = {
            LottieEmpty()
        }
    ) { carousel ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            item { TitleSection(AnimeType.SPOTLIGHT) }
            item { CarouselHome(carousel = carousel) }

            item { TitleSection(AnimeType.ONGOING, navigator)}
            item { ScrollableAnimeItem(navigator = navigator, items = viewModel.ongoing) }

            item { TitleSection(AnimeType.MOVIE, navigator)}
            item { ScrollableAnimeItem(navigator = navigator, items = viewModel.movie) }


            item { TitleSection(AnimeType.FINISHED, navigator) }
            item { ScrollableAnimeItem(navigator = navigator, items = viewModel.finished) }

        }
    }

}

@Composable
fun TitleSection(type: AnimeType, navigator: DestinationsNavigator? = EmptyDestinationsNavigator) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .clickable(
                enabled = type.query?.isNotEmpty() == true,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color.LightGray)
            ) {
                navigator?.navigate(AnimeScreenDestination(type = type))
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = type.title,
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(all = 4.dp)
                .padding(top = 4.dp, bottom = 4.dp)
        )

        if (type.query?.isNotEmpty() == true) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                tint = Color.White,
                modifier = Modifier.size(24.dp),
                contentDescription = "Arrow"
            )
        }

    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(EmptyDestinationsNavigator)
}