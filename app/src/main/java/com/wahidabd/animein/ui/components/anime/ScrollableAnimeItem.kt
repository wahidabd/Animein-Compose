package com.wahidabd.animein.ui.components.anime

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wahidabd.animein.domain.anime.model.Anime
import com.wahidabd.animein.screen.destinations.AnimeDetailScreenDestination
import com.wahidabd.animein.utils.collectStateFlow
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.StateFlow


/**
 * Created by Wahid on 7/15/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun ScrollableAnimeItem(
    navigator: DestinationsNavigator,
    items: StateFlow<Resource<List<Anime>>>
) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth(),
    ) {
        items.collectStateFlow(
            onLoading = {},
            onFailure = { _, _ ->

            }
        ) { anime ->
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(anime) { item ->
                    AnimeItem(data = item, onClick = {
                        navigator.navigate(
                            AnimeDetailScreenDestination(
                                slug = item.slug.toString(),
                                title = item.title.toString()
                            )
                        )
                    })
                }
            }
        }
    }
}