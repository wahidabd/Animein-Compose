package com.wahidabd.animeku.ui.screen.bookmark

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wahidabd.animeku.ui.component.HeaderBackButton
import com.wahidabd.animeku.ui.component.anime.AnimePagingItem
import com.wahidabd.animeku.ui.component.lottie.LottieEmpty
import com.wahidabd.animeku.ui.component.lottie.LottieError
import com.wahidabd.animeku.ui.component.lottie.LottieLoading
import com.wahidabd.animeku.ui.screen.destinations.AnimeDetailScreenDestination
import com.wahidabd.animeku.utils.rememberForeverLazyListGridState
import com.wahidabd.library.utils.exts.collectStateFlow


/**
 * Created by wahid on 11/15/2023.
 * Github github.com/wahidabd.
 */


@Destination
@Composable
fun BookmarkScreen(
    navigator: DestinationsNavigator,
    viewModel: BookmarkViewModel = hiltViewModel()
) {

    val state: LazyGridState = rememberForeverLazyListGridState(key = "bookmark_screen")

    LaunchedEffect(Unit) {
        viewModel.getAll()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        HeaderBackButton(
            title = "Bookmark",
            backButton = false,
            searchIcon = true,
            removeIcon = true,
            onBackClick = {}
        )

        viewModel.animeList.collectStateFlow(
            onLoading = { LottieLoading() },
            onEmpty = { LottieEmpty() },
            onFailure = { _, message -> LottieError(error = message) },
            onSuccess = { animeList ->
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
                    items(animeList) { anime ->
                        AnimePagingItem(
                            data = anime,
                            onclick = { slug ->
                                navigator.navigate(AnimeDetailScreenDestination(slug))
                            }
                        )
                    }
                }
            }
        )
    }
}