package com.wahidabd.animeku.ui.screen.anime

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wahidabd.animeku.ui.component.GenreList
import com.wahidabd.animeku.ui.component.HeaderBackButton
import com.wahidabd.animeku.ui.component.anime.AnimeHeader
import com.wahidabd.animeku.ui.component.anime.SynopsisContent
import com.wahidabd.animeku.ui.component.lottie.LottieError
import com.wahidabd.animeku.ui.component.lottie.LottieLoading
import com.wahidabd.animeku.ui.theme.ColorPrimary
import com.wahidabd.library.utils.exts.collectStateFlow


/**
 * Created by wahid on 11/19/2023.
 * Github github.com/wahidabd.
 */

@Destination
@Composable
fun AnimeDetailScreen(
    slug: String,
    navigator: DestinationsNavigator,
    viewModel: AnimeViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getAnimeDetail(slug)
        viewModel.getEpisodes(slug)
    }

    val listState = rememberLazyListState()
    var isSynopsisExpanded by remember { mutableStateOf(false) }
    val isCollapsed: Boolean by remember {
        derivedStateOf {
            listState.firstVisibleItemIndex != 0
        }
    }

    val episodes = viewModel.episode.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorPrimary)
    ) {
        viewModel.anime.collectStateFlow(
            onLoading = { LottieLoading() },
            onFailure = { _, message ->
                LottieError(error = message.toString())
            }
        ) { anime ->
            Box(modifier = Modifier.fillMaxWidth()) {
                LazyColumn(
                    state = listState,
                    modifier = Modifier.fillMaxSize()
                ) {
                    item { AnimeHeader(anime = anime) }
                    item { Spacer(modifier = Modifier.height(90.dp)) }
                    item {
                        SynopsisContent(
                            synopsis = anime.synopsis ?: "No Content",
                            isExpanded = isSynopsisExpanded,
                            onExpandChange = { isSynopsisExpanded = it }
                        )
                    }
                    item {
                        GenreList(
                            genres = anime.genres ?: emptyList(),
                            expanded = isSynopsisExpanded
                        )
                    }

                    lazyEpisode(episodes.value,
                        onclick = { url -> },
                        onDownload = { slug -> },
                        onError = {}
                    )
                }

                // Appbar
                HeaderBackButton(
                    color = if (isCollapsed) ColorPrimary else Color.Transparent,
                    title = anime.title,
                    onBackClick = { navigator.navigateUp() },
                    modifier = Modifier
                )
            }
        }
    }
}