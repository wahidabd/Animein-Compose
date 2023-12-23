package com.wahidabd.animeku.ui.component.anime

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import com.gowtham.ratingbar.RatingBarStyle
import com.gowtham.ratingbar.StepSize
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import com.wahidabd.animeku.R
import com.wahidabd.animeku.domain.anime.model.AnimeDetail
import com.wahidabd.animeku.ui.component.TextBackgroundDarkGray
import com.wahidabd.animeku.ui.theme.ColorPrimary
import com.wahidabd.animeku.ui.theme.ColorSecondary


/**
 * Created by wahid on 11/19/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun AnimeHeader(
    anime: AnimeDetail,
    isFavorite: Boolean = false,
    onBookmark: () -> Unit,
    onComment: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp)
    ) {
        val (backdrop,
            title,
            poster,
            translucentBar
        ) = createRefs()

        CoilImage(
            imageModel = anime.poster,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .constrainAs(backdrop) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            failure = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.backdrop_not_available),
                        contentDescription = "no image"
                    )
                }
            },
            shimmerParams = ShimmerParams(
                baseColor = ColorPrimary,
                highlightColor = ColorSecondary,
                durationMillis = 500,
                dropOff = 0.65F,
                tilt = 20F
            ),
            previewPlaceholder = R.drawable.ic_load_placeholder,
            contentScale = ContentScale.Crop,
            contentDescription = "Header backdrop image",
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            ColorPrimary.copy(alpha = 0.5F),
                            ColorPrimary
                        ),
                        startY = 0.1F
                    )
                )
                .constrainAs(translucentBar) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(backdrop.bottom)
                }
        )

        CoilImage(
            imageModel = anime.poster,
            modifier = Modifier
                .padding(16.dp)
                .clip(RoundedCornerShape(4.dp))
                .width(115.dp)
                .height(172.5.dp)
                .constrainAs(poster) {
                    top.linkTo(backdrop.bottom)
                    bottom.linkTo(backdrop.bottom)
                    start.linkTo(parent.start)
                }, failure = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.image_not_available),
                        contentDescription = "no image"
                    )
                }
            },
            shimmerParams = ShimmerParams(
                baseColor = ColorPrimary,
                highlightColor = ColorSecondary,
                durationMillis = 500,
                dropOff = 0.65F,
                tilt = 20F
            ),
            previewPlaceholder = R.drawable.img_sample,
            contentScale = ContentScale.Crop,
            circularReveal = CircularReveal(duration = 1000),
            contentDescription = "movie poster"
        )

        Column(
            modifier = Modifier.constrainAs(title) {
                start.linkTo(poster.end, margin = 12.dp)
                end.linkTo(parent.end, margin = 12.dp)
                bottom.linkTo(poster.bottom)
                top.linkTo(poster.top)
            },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                TextBackgroundDarkGray(text = anime.type ?: "No Type")
                Spacer(modifier = Modifier.width(10.dp))
                TextBackgroundDarkGray(text = anime.duration ?: "Unknown")
            }

            Text(
                text = anime.title.toString(),
                modifier = Modifier
                    .padding(top = 2.dp, start = 4.dp, bottom = 4.dp, end = 4.dp)
                    .fillMaxWidth(0.6F),
                maxLines = 2,
                style = MaterialTheme.typography.displayMedium,
                color = Color.White.copy(alpha = 0.78F)
            )

            Text(
                text = anime.releaseDate.toString(),
                modifier = Modifier.padding(start = 4.dp, bottom = 4.dp),
                style = MaterialTheme.typography.titleSmall,
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.56F)
            )

            val rating = if (anime.rating?.isNotBlank() == true) {
                anime.rating.toFloat()
            } else 0F

            RatingBar(
                value = rating.div(2) ?: 0F,
                modifier = Modifier.padding(start = 6.dp, bottom = 4.dp, top = 4.dp),
                config = RatingBarConfig()
                    .style(RatingBarStyle.Normal)
                    .isIndicator(true)
                    .activeColor(Color(0XFFC9F964))
                    .hideInactiveStars(false)
                    .inactiveColor(Color.LightGray.copy(alpha = 0.3F))
                    .stepSize(StepSize.HALF)
                    .numStars(5)
                    .size(16.dp)
                    .padding(4.dp),
                onValueChange = {},
                onRatingChanged = {}
            )


            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 4.dp, bottom = 8.dp)
                    .fillMaxWidth(0.42F),
            ) {
                IconButton(onClick = { onComment() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_chat),
                        tint = Color.White.copy(alpha = 0.45F),
                        contentDescription = "comment icon",
                        modifier = Modifier.size(22.dp)
                    )
                }

                IconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_download),
                        tint = Color.White.copy(alpha = 0.45F),
                        contentDescription = "download icon",
                        modifier = Modifier.size(22.dp)
                    )
                }

                IconButton(onClick = { onBookmark() }) {
                    Icon(
                        painter = painterResource(id = if (!isFavorite) R.drawable.ic_bookmark else R.drawable.ic_bookmark_filled),
                        tint = Color.White.copy(alpha = 0.45F),
                        contentDescription = "add to watch list icon",
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AnimeHeaderDetailPreview() {
    AnimeHeader(
        AnimeDetail(
            "",
            "",
            "One Piece",
            "TV",
            "Ongoing",
            "21 min",
            "fall 1999",
            "8.0",
            totalEpisode = "1089",
            releaseDate = "1 Oktober 2002",
            studio = "Madhouse"
        ),
        onBookmark = {},
        onComment = {}
    )
}