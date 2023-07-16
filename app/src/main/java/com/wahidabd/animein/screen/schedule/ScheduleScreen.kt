package com.wahidabd.animein.screen.schedule

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.wahidabd.animein.screen.anime.AnimeViewModel
import com.wahidabd.animein.ui.components.anime.AnimePagingItem
import com.wahidabd.animein.ui.components.lottie.LottieError
import com.wahidabd.animein.ui.components.lottie.LottieLoading
import com.wahidabd.animein.ui.components.navigation.scheduleTabs
import com.wahidabd.animein.ui.theme.ColorPrimary
import com.wahidabd.library.utils.common.emptyString
import kotlinx.coroutines.launch


/**
 * Created by Wahid on 7/16/2023.
 * Github github.com/wahidabd.
 */


@OptIn(ExperimentalFoundationApi::class)
@Destination
@Composable
fun ScheduleScreen(
    navigator: DestinationsNavigator,
    viewModel: AnimeViewModel = hiltViewModel()
) {

    val pagerState = rememberPagerState(pageCount = { scheduleTabs.size })
    val pagerPage = remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = pagerState.currentPage) {
        pagerPage.value = pagerState.currentPage
        viewModel.anime(scheduleTabs[pagerPage.value].endpoint)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorPrimary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ScrollableTabRow(
                backgroundColor = Color.Transparent,
                contentColor = Color.White,
                edgePadding = 0.dp,
                selectedTabIndex = minOf(scheduleTabs.count(), pagerPage.value),
                tabs = {
                    scheduleTabs.forEachIndexed { index, item ->
                        Tab(
                            onClick = {
                                pagerPage.value = index
                                scope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                            },
                            text = {
                                Text(
                                    text = item.title,
                                    style = MaterialTheme.typography.titleSmall,
                                    fontSize = 12.sp,
                                    color = Color.White
                                )
                            },
                            selected = index == pagerState.currentPage
                        )
                    }
                }
            )

            HorizontalPager(
                state = pagerState,
                verticalAlignment = Alignment.Top,
                userScrollEnabled = true
            ) {
                val animePaging = viewModel.anime.value.collectAsLazyPagingItems()

                when (animePaging.loadState.refresh) {
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
                            items(animePaging.itemCount) { index ->
                                AnimePagingItem(data = animePaging[index]!!) {}
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

}

@Preview
@Composable
fun ScheduleScreenPreview() {
    ScheduleScreen(EmptyDestinationsNavigator)
}