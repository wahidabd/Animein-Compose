package com.wahidabd.animein.ui.components.anime

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import com.wahidabd.animein.R
import com.wahidabd.animein.domain.anime.model.Anime
import com.wahidabd.animein.ui.components.utils.TextRectangleDarkGray
import com.wahidabd.animein.ui.components.utils.TextRectangleOrange
import com.wahidabd.animein.ui.theme.ColorDarkGray
import com.wahidabd.animein.ui.theme.ColorPrimary
import com.wahidabd.animein.ui.theme.ColorTranslucentBlack


/**
 * Created by Wahid on 7/16/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun AnimePagingItem(
    data: Anime,
    onclick: () -> Unit
) {

    val gradient = Brush.verticalGradient(listOf(Color.Transparent, ColorTranslucentBlack))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(248.dp)
            .clip(RoundedCornerShape(6.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color.LightGray)
            ) {},
    ) {
        CoilImage(
            imageModel = data.poster,
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
                        painter = painterResource(id = R.drawable.image_not_available),
                        contentDescription = "no image"
                    )
                }
            },
            contentScale = ContentScale.Crop,
            circularReveal = CircularReveal(duration = 700),
            contentDescription = data.title,
            modifier = Modifier
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradient, blendMode = BlendMode.Multiply)
                    }
                }
        )

        TextRectangleOrange(
            text = data.episode.toString(),
            modifier = Modifier.padding(4.dp)
        )


        Column(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
                .align(Alignment.BottomStart)
        ) {

            Row {
                TextRectangleDarkGray(text = data.type.toString())
                Spacer(modifier = Modifier.width(4.dp))
                TextRectangleDarkGray(text = data.resolution.toString())
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = data.title.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                fontSize = 12.sp,
                maxLines = 2
            )
        }


    }
}

@Preview
@Composable
fun AnimePagingItemPreview() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        AnimePagingItem(
            Anime(
                "",
                "",
                "One Piece",
                "Movie",
                "1/12",
                "8.9",
            ), onclick = {}
        )
        Spacer(modifier = Modifier.width(12.dp))
        AnimePagingItem(
            Anime(
                "",
                "",
                "One Piece",
                "Movie",
                "1/12",
                "8.9",
            ), onclick = {}
        )
    }
}