package com.wahidabd.animein.screen.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wahidabd.animein.domain.anime.model.Anime
import com.wahidabd.animein.ui.components.anime.AnimePagingItem
import com.wahidabd.animein.ui.components.lottie.LottieError
import com.wahidabd.animein.ui.components.lottie.LottieLoading
import com.wahidabd.animein.ui.theme.ColorPrimary
import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 7/16/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun ScheduleTabScreen(
    navigator: DestinationsNavigator,
    anime: LazyPagingItems<Anime>
) {

    Column(
        modifier = Modifier
            .background(ColorPrimary)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        when (anime.loadState.refresh) {
            is LoadState.Loading -> {
                LottieLoading()
            }

            is LoadState.NotLoading -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    items(anime.itemCount) { index ->
                        AnimePagingItem(data = anime[index]!!) {}
                    }
                }
            }

            is LoadState.Error -> {
                LottieError(error = emptyString())
            }
        }
    }
}