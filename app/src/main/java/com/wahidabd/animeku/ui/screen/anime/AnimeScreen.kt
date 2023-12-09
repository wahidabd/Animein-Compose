package com.wahidabd.animeku.ui.screen.anime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.network.HttpException
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wahidabd.animeku.ui.component.HeaderBackButton
import com.wahidabd.animeku.ui.component.anime.AnimePagingItem
import com.wahidabd.animeku.ui.component.lottie.LottieLoading
import com.wahidabd.animeku.ui.screen.destinations.AnimeDetailScreenDestination
import com.wahidabd.animeku.ui.theme.ColorDarkPurple
import com.wahidabd.animeku.ui.theme.ColorPrimary
import com.wahidabd.animeku.utils.enums.AnimeType
import com.wahidabd.animeku.utils.rememberForeverLazyListGridState
import java.io.IOException


/**
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */


@Destination
@Composable
fun AnimeScreen(
    navigator: DestinationsNavigator,
    type: AnimeType = AnimeType.ONGOING,
    viewModel: AnimeViewModel = hiltViewModel()
) {

    val state: LazyGridState = rememberForeverLazyListGridState(key = "anime_screen")

    LaunchedEffect(Unit) {
        viewModel.paging(type.query.toString())
    }


    val pagingData = viewModel.paging.value.collectAsLazyPagingItems()
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderBackButton(title = type.label, onBackClick = { navigator.navigateUp() })

        when (pagingData.loadState.refresh) {
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
                    items(pagingData.itemCount) { index ->
                        AnimePagingItem(data = pagingData[index]) { slug ->
                            // handle navigation to detail
                            navigator.navigate(AnimeDetailScreenDestination(slug = slug))
                        }
                    }
                }
            }

            is LoadState.Error -> {}
        }

        when (pagingData.loadState.append) {
            is LoadState.Error -> {
                val error = pagingData.loadState.refresh as LoadState.Error
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