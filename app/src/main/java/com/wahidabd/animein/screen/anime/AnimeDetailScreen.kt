package com.wahidabd.animein.screen.anime

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.wahidabd.animein.ui.components.detail.AnimeGenreChip
import com.wahidabd.animein.ui.components.detail.AnimeHeaderDetail
import com.wahidabd.animein.ui.components.detail.SynopsisContent
import com.wahidabd.animein.ui.components.lottie.LottieError
import com.wahidabd.animein.ui.components.lottie.LottieLoading
import com.wahidabd.animein.ui.components.utils.AnimeHeader
import com.wahidabd.animein.ui.theme.ColorPrimary
import com.wahidabd.animein.utils.collectStateFlow
import com.wahidabd.library.utils.extensions.debug


/**
 * Created by Wahid on 7/17/2023.
 * Github github.com/wahidabd.
 */


@Destination
@Composable
fun AnimeDetailScreen(
    navigator: DestinationsNavigator,
    slug: String,
    title: String,
    viewModel: AnimeViewModel = hiltViewModel()
) {


    LaunchedEffect(Unit) {
        viewModel.detail(slug)
    }

    val listState: LazyListState = rememberLazyListState()
    var isSynopsisExpanded by remember { mutableStateOf(false) }

    val isCollapsed: Boolean by remember {
        derivedStateOf {
            listState.firstVisibleItemScrollOffset > 350
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorPrimary)
    ) {

        viewModel.detail.collectStateFlow(
            onLoading = {
                LottieLoading()
            },
            onFailure = { _, m ->
                LottieError(error = m.toString())
            }
        ) { anime ->
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
                    item { AnimeHeaderDetail(anime = anime) }
                    item { Spacer(modifier = Modifier.height(90.dp)) }

                    // Synopsis
                    item {
                        SynopsisContent(
                            synopsis = anime.synopsis.toString(),
                            alternative = anime.alternative,
                            isExpanded = isSynopsisExpanded,
                            onExpandChange = { isSynopsisExpanded = it })
                    }

                    // Genre
                    item {
                        val genres = anime.genres
                        debug { "Genre -> $genres" }

                        if (genres?.isNotEmpty() == true) {
                            if (isSynopsisExpanded) {
                                FlowRow(
                                    modifier = Modifier.padding(horizontal = 10.dp),
                                    lastLineMainAxisAlignment = FlowMainAxisAlignment.Start,
                                    mainAxisSpacing = 4.dp,
                                    crossAxisSpacing = 4.dp
                                ) {
                                    genres.forEach { genre ->
                                        AnimeGenreChip(text = genre.name)
                                    }
                                }
                            } else {
                                LazyRow(
                                    contentPadding = PaddingValues(
                                        horizontal = 10.dp,
                                        vertical = 4.dp
                                    ),
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    this.items(genres) { genre ->
                                        AnimeGenreChip(text = genre.name)
                                    }
                                }
                            }
                        }
                    }

                }

                // header
                AnimeHeader(
                    color = if (isCollapsed) ColorPrimary else Color.Transparent,
                    title = anime.title,
                    backOnClick = { navigator.navigateUp() }
                )
            }
        }
    }

}