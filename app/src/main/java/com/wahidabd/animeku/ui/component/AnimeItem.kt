package com.wahidabd.animeku.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage
import com.wahidabd.animeku.R
import com.wahidabd.animeku.domain.anime.model.Anime
import com.wahidabd.animeku.ui.theme.Shapes
import com.wahidabd.animeku.utils.shimmerParams


/**
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun AnimeItem(
    data: Anime,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .padding(all = 4.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color.LightGray)
            ) {
                onClick()
            }
            .width(134.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        Box(
            modifier = Modifier
                .width(130.dp)
                .height(195.dp)
                .clip(Shapes.small)
        ) {
            CoilImage(
                imageModel = data.poster,
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
                            painter = painterResource(id = R.drawable.image_not_available),
                            contentDescription = "no image"
                        )
                    }
                },
                previewPlaceholder = R.drawable.img_sample,
                contentScale = ContentScale.Crop,
                circularReveal = CircularReveal(duration = 700),
                modifier = Modifier.clip(RoundedCornerShape(8.dp)),
                contentDescription = data.title
            )

            TextRectangleOrange(
                text = data.rating.toString(),
                modifier = Modifier
                    .padding(4.dp)
            )


            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(4.dp)
            ) {
                TextRectangleDarkGray(text = data.type.toString())
            }
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = data.title.toString(),
            style = MaterialTheme.typography.titleSmall,
            color = Color.LightGray,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )
    }
}