package com.wahidabd.animein.ui.components.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.wahidabd.animein.ui.theme.ColorOrange
import com.wahidabd.animein.ui.theme.ColorPrimary
import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 7/17/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun SynopsisContent(
    synopsis: String,
    isExpanded: Boolean,
    alternative: String? = emptyString(),
    onExpandChange: (Boolean) -> Unit
) {

    val fullSynopsis =
        if (alternative?.isNotEmpty() == true) "$synopsis\n\nAlternate title: $alternative" else synopsis
    AnimatedContent(
        targetState = isExpanded,
        transitionSpec = {
            expandVertically(animationSpec = tween(150, 150), initialHeight = { it }) togetherWith
                    shrinkVertically(animationSpec = tween(150, 150), targetHeight = { it }) using
                    SizeTransform(clip = true)
        },
        label = ""
    ) { targetExpanded ->
        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { onExpandChange(!isExpanded) }
                )
        ) {
            if (targetExpanded) {
                Text(
                    text = fullSynopsis,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Justify,
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 24.dp)
                )
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "Shrink",
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .zIndex(2F)
                )
            } else {
                Text(
                    text = fullSynopsis,
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 12.sp,
                    color = Color.White,
                    textAlign = TextAlign.Justify,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 4.dp)
                )

                Box(
                    modifier = Modifier
                        .zIndex(1F)
                        .fillMaxSize()
                        .align(Alignment.BottomCenter)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    ColorPrimary.copy(alpha = 0.9F),
                                    ColorPrimary
                                )
                            )
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Expand",
                        tint = Color.White,
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .zIndex(2F)
                    )
                }
            }
        }
    }
}
