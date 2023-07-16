package com.wahidabd.animein.ui.components.anime

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import com.wahidabd.animein.R
import com.wahidabd.animein.domain.anime.model.Anime
import com.wahidabd.animein.ui.theme.ColorDarkGray
import com.wahidabd.animein.ui.theme.ColorPrimary


/**
 * Created by Wahid on 7/16/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun AnimePagingItem(
    data: Anime,
    onclick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(248.dp)
            .clip(RoundedCornerShape(6.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color.LightGray)
            ) {}
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
            previewPlaceholder = R.drawable.img_sample,
            contentScale = ContentScale.Crop,
            circularReveal = CircularReveal(duration = 700),
            contentDescription = data.title
        )
    }
}

@Preview
@Composable
fun AnimePagingItemPreview() {
    AnimePagingItem(Anime(
        "",
        "",
        "One Piece",
        "Movie",
        "1/12",
        "8.9",
    ), onclick = {})
}