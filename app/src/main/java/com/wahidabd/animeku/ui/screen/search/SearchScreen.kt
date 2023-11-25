package com.wahidabd.animeku.ui.screen.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wahidabd.animeku.ui.component.SearchBar
import com.wahidabd.animeku.ui.component.anime.AnimePagingItem
import com.wahidabd.animeku.ui.component.lottie.LottieEmpty
import com.wahidabd.animeku.ui.component.lottie.LottieError
import com.wahidabd.animeku.ui.component.lottie.LottieSearch
import com.wahidabd.animeku.ui.screen.destinations.AnimeDetailScreenDestination
import com.wahidabd.animeku.ui.theme.ColorPrimary
import com.wahidabd.animeku.utils.rememberForeverLazyListGridState
import com.wahidabd.library.utils.common.emptyString


/**
 * Created by wahid on 11/15/2023.
 * Github github.com/wahidabd.
 */


@Destination
@Composable
fun SearchScreen(
    navigator: DestinationsNavigator,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val state: LazyGridState = rememberForeverLazyListGridState(key = "search")

    Column(
        modifier = Modifier
            .background(ColorPrimary)
            .fillMaxSize()
    ) {
        SearchBar(
            autoFocus = true,
            onSearch = {viewModel.search()},
            viewModel = viewModel
        )

        if (viewModel.searchParam.value.trim().isNotBlank()) {
            val animePaging = viewModel.anime.value.collectAsLazyPagingItems()

            if (animePaging.loadState.refresh is LoadState.NotLoading &&
                animePaging.loadState.append.endOfPaginationReached &&
                animePaging.itemCount < 1
            ) {
                LottieEmpty()
            }

            when (animePaging.loadState.refresh) {
                is LoadState.Loading -> {
                    LottieSearch()
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
        }

    }
}
