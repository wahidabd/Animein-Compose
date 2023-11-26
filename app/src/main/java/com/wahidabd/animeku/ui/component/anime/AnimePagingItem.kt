package com.wahidabd.animeku.ui.component.anime

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage
import com.wahidabd.animeku.R
import com.wahidabd.animeku.domain.anime.model.Anime
import com.wahidabd.animeku.ui.component.TextRectangleDarkGray
import com.wahidabd.animeku.ui.component.TextRectangleOrange
import com.wahidabd.animeku.ui.theme.ColorDarkGray
import com.wahidabd.animeku.ui.theme.ColorPrimary
import com.wahidabd.animeku.utils.gradientVerticalBrush


/**
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AnimePagingItem(
    data: Anime?,
    onclick: (slug: String) -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(248.dp)
            .clip(RoundedCornerShape(6.dp))
            .combinedClickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color.LightGray),
                onClick = { onclick.invoke(data?.slug.toString()) },
                onLongClick = {},
            ),
    ) {
        CoilImage(
            imageModel = data?.poster,
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
            contentDescription = data?.title,
            modifier = Modifier
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(gradientVerticalBrush, blendMode = BlendMode.Multiply)
                    }
                }
        )

        TextRectangleOrange(
            text = data?.rating ?: "?",
            modifier = Modifier.padding(4.dp)
        )


        Column(
            modifier = Modifier
                .padding(6.dp)
                .fillMaxWidth()
                .align(Alignment.BottomStart)
        ) {

            TextRectangleDarkGray(text = data?.type.toString())
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = data?.title.toString(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White,
                fontSize = 12.sp,
                maxLines = 2
            )
        }
    }
}