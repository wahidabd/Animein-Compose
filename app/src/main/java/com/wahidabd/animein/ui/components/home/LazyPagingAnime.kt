package com.wahidabd.animein.ui.components.home

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemsIndexed
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wahidabd.animein.data.anime.model.AnimeResponse


/**
 * Created by Wahid on 7/10/2023.
 * Github github.com/wahidabd.
 */


fun LazyListScope.lazyPagingAnime(
    items: LazyPagingItems<AnimeResponse>,
    navigator: DestinationsNavigator,
) {
    when (items.loadState.refresh) {
        is LoadState.Loading -> {}

        is LoadState.NotLoading -> {
            itemsIndexed(items) { _, value ->
                val sizeSize: Dp = (LocalConfiguration.current.screenWidthDp.dp / 2)
                FlowRow(
                    mainAxisSize = SizeMode.Expand,
                    mainAxisAlignment = FlowMainAxisAlignment.SpaceBetween
                ) {
                    AnimeItem(data = value!!, modifier = Modifier.size(sizeSize)) {}
                }
            }
        }

        is LoadState.Error -> {}
    }
}

