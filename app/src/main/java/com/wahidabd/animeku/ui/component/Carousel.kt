package com.wahidabd.animeku.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.wahidabd.animeku.R
import com.wahidabd.animeku.domain.anime.model.Anime
import com.wahidabd.animeku.ui.theme.ColorOrange
import com.wahidabd.animeku.ui.theme.ColorWhite
import com.wahidabd.animeku.ui.theme.Shapes
import com.wahidabd.animeku.utils.constants.Constants
import com.wahidabd.animeku.utils.carouselTransition
import com.wahidabd.animeku.utils.gradientVerticalBrush
import com.wahidabd.animeku.utils.shimmerParams
import com.wahidabd.library.utils.common.emptyString
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Carousel(
    animeList: List<Anime>,
    autoScrollDuration: Long = Constants.CAROUSEL_AUTO_SCROLL_TIMER
) {

    val pageCount = animeList.size
    val pagerState: PagerState = rememberPagerState(pageCount = { pageCount })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()) {
        with(pagerState) {
            if (pageCount > 0) {
                var currentPage by remember { mutableIntStateOf(0) }
                LaunchedEffect(key1 = currentPage) {
                    launch {
                        delay(timeMillis = autoScrollDuration)
                        val nextPage = (currentPage + 1).mod(pageCount)
                        animateScrollToPage(page = nextPage)
                        currentPage = nextPage
                    }
                }
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            HorizontalPager(
                state = pagerState,
                pageSpacing = dimensionResource(id = R.dimen.normal_padding)
            ) { page ->
                val item = animeList[page]
                Card(
                    onClick = {},
                    modifier = Modifier
                        .carouselTransition(page, pagerState)
                ) {
                    CarouselItem(item = item)
                }
            }
        }
    }
}

@Composable
fun CarouselItem(item: Anime) {
    Box {
        CoilImage(
            imageModel = item.poster ?: emptyString(),
            shimmerParams = shimmerParams(),
            failure = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = Color.LightGray,
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_load_error),
                        contentDescription = "no image"
                    )
                }
            },
            previewPlaceholder = R.drawable.ic_load_placeholder,
            contentScale = ContentScale.Crop,
            circularReveal = CircularReveal(duration = 700),
            modifier = Modifier
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradientVerticalBrush, blendMode = BlendMode.Multiply)
                    }
                }
                .height(dimensionResource(id = R.dimen.home_grid_card_height))
                .fillMaxWidth(),
            contentDescription = item.title
        )

        Text(
            text = item.title ?: "No Title",
            style = MaterialTheme.typography.headlineMedium,
            color = ColorWhite,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxWidth()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.normal_padding),
                    vertical = 12.dp
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 6.dp,
                    horizontal = 10.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextRectangleOrange(text = item.rating ?: "?", fontSize = 12.sp)
        }
    }
}

@Preview
@Composable
fun CarouselItemPreview() {
    Carousel(
        listOf(Anime(title = "On Piece", rating = "7.6", type = "TV"))
    )
}