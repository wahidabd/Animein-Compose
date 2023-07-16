package com.wahidabd.animein.ui.components.anime

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import com.wahidabd.animein.R
import com.wahidabd.animein.domain.anime.model.Carousel
import com.wahidabd.animein.ui.theme.ColorDarkGray
import com.wahidabd.animein.ui.theme.ColorPrimary
import com.wahidabd.animein.ui.theme.ColorTranslucentBlack
import com.wahidabd.animein.utils.Constant
import com.wahidabd.animein.utils.carouselTransition
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CarouselHome(
    carousel: List<Carousel>,
    autoScrollDuration: Long = Constant.CAROUSEL_AUTO_SCROLL_TIMER
) {

    val pageCount = carousel.size
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
//                contentPadding = PaddingValues(
//                    horizontal = dimensionResource(id = R.dimen.normal_padding)
//                ),
                pageSpacing = dimensionResource(id = R.dimen.normal_padding)
            ) { page ->
                val item = carousel[page]
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
fun CarouselItem(item: Carousel) {

    val gradient =
        Brush.verticalGradient(listOf(Color.Transparent, ColorTranslucentBlack))

    Box {
        CoilImage(
            imageModel = item.image,
            shimmerParams = ShimmerParams(
                baseColor = ColorPrimary,
                highlightColor = ColorDarkGray,
                durationMillis = 500,
                dropOff = 0.65F,
                tilt = 20F
            ),
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
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                }
                .height(dimensionResource(id = R.dimen.home_grid_card_height))
                .fillMaxWidth(),
            contentDescription = item.title
        )

        Text(
            text = item.title.toString(),
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White,
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
    }
}

@Preview
@Composable
fun CarouselHomePreview() {
    CarouselHome(
        listOf(
            Carousel(
                slug = "",
                image = "",
                title = "One Piece"
            )
        )
    )
}