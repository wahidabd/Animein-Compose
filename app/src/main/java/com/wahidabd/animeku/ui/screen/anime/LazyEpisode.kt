package com.wahidabd.animeku.ui.screen.anime

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wahidabd.animeku.domain.anime.model.Episode
import com.wahidabd.animeku.ui.component.CircularLoading
import com.wahidabd.animeku.ui.component.EpisodeItem
import com.wahidabd.library.data.Resource


/**
 * Created by wahid on 11/20/2023.
 * Github github.com/wahidabd.
 */


fun LazyListScope.lazyEpisode(
    state: Resource<List<Episode>>,
    onclick: (url: String) -> Unit,
    onDownload: (String) -> Unit,
    onError: () -> Unit,
) {
    when (state) {
        is Resource.Default -> {}
        is Resource.Loading -> {
            item { CircularLoading() }
        }

        is Resource.Empty -> {}
        is Resource.Failure -> {}
        is Resource.Success -> {
            items(state.data) { episode ->
                EpisodeItem(
                    episode = episode,
                    onclick = { onclick(episode.slug.toString()) },
                    onDownload = { },
                    modifier = Modifier.padding(
                        horizontal = 10.dp, vertical = 4.dp
                    )
                )
            }
        }
    }

}