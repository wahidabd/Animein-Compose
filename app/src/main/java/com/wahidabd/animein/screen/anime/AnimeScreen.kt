package com.wahidabd.animein.screen.anime

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.network.HttpException
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.wahidabd.animein.screen.destinations.AnimeDetailScreenDestination
import com.wahidabd.animein.ui.components.anime.AnimePagingItem
import com.wahidabd.animein.ui.components.lottie.LottieError
import com.wahidabd.animein.ui.components.lottie.LottieLoading
import com.wahidabd.animein.ui.components.utils.AnimeHeader
import com.wahidabd.animein.ui.theme.ColorDarkPurple
import com.wahidabd.animein.ui.theme.ColorPrimary
import com.wahidabd.animein.ui.theme.ColorSecondary
import com.wahidabd.animein.utils.enums.AnimeType
import com.wahidabd.library.utils.common.emptyString
import okio.IOException


/**
 * Created by Wahid on 7/15/2023.
 * Github github.com/wahidabd.
 */

@Destination
@Composable
fun AnimeScreen(
    navigator: DestinationsNavigator,
    type: AnimeType = AnimeType.ONGOING,
    viewModel: AnimeViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.anime(type.query.toString())
    }

    val animePaging = viewModel.anime.value.collectAsLazyPagingItems()
    val state: LazyGridState = rememberLazyGridState()

    Column(
        modifier = Modifier
            .background(ColorPrimary)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AnimeHeader(title = type.title, backOnClick = { navigator.navigateUp() })
        Divider(color = ColorSecondary)

        when (animePaging.loadState.refresh) {
            is LoadState.Loading -> {
                LottieLoading()
            }

            is LoadState.NotLoading -> {
                LazyVerticalGrid(
                    state = state,
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    items(animePaging.itemCount) { index ->
                        AnimePagingItem(data = animePaging[index]!!) {
                            navigator.navigate(
                                AnimeDetailScreenDestination(
                                    slug = animePaging[index]?.slug.toString()
                                )
                            )
                        }
                    }
                }
            }

            is LoadState.Error -> {
                LottieError(error = emptyString())
            }
        }

        when (animePaging.loadState.append) {
            is LoadState.Error -> {
                val error = animePaging.loadState.refresh as LoadState.Error
                val errorMessage = when (error.error) {
                    is HttpException -> "Sorry, Something went wrong!\nTap to retry"
                    is IOException -> "Connection failed. Tap to retry!"
                    else -> "Failed! Tap to retry!"
                }

                Text(
                    text = errorMessage,
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = ColorPrimary,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            is LoadState.Loading -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    CircularProgressIndicator(color = ColorDarkPurple)
                }
            }

            else -> {}
        }
    }
}

@Preview
@Composable
fun AnimeScreenPreview() {
    AnimeScreen(navigator = EmptyDestinationsNavigator, AnimeType.MOVIE)
}