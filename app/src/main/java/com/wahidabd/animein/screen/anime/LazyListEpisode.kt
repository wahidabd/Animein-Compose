package com.wahidabd.animein.screen.anime

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.wahidabd.animein.domain.anime.model.Episode
import com.wahidabd.animein.ui.components.detail.EpisodeItem


/**
 * Created by Wahid on 7/18/2023.
 * Github github.com/wahidabd.
 */


fun LazyListScope.lazyListEpisode(
    episodes: LazyPagingItems<Episode>,
    onclick: () -> Unit,
    onDownload: (String) -> Unit,
    onError: () -> Unit
) {
    when (episodes.loadState.refresh) {
        is LoadState.Loading -> {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
        }

        is LoadState.Error -> {}

        is LoadState.NotLoading -> {
            items(episodes) { episode ->
                EpisodeItem(
                    episode = episode!!,
                    onclick = { onclick() },
                    onDownload = { onDownload(it) },
                    modifier = Modifier.padding(
                        horizontal = 10.dp, vertical = 4.dp
                    )
                )
            }
        }
    }

    when (episodes.loadState.append) {
        is LoadState.Error -> {}
        is LoadState.Loading -> {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
        }

        else -> {}
    }
}