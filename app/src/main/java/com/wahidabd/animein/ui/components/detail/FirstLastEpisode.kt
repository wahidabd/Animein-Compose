package com.wahidabd.animein.ui.components.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wahidabd.animein.domain.anime.model.Episode
import com.wahidabd.animein.utils.enums.IconPosition


/**
 * Created by Wahid on 7/18/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun FirstLastEpisode(
    episode: Episode,
    onclick: (String) -> Unit,
    position: IconPosition = IconPosition.LEFT,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color.DarkGray)
            ) {onclick(episode.slug)},
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (position == IconPosition.LEFT){
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                tint = Color.White
            )
        }
        Text(
            text = episode.name,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 12.sp,
            color = Color.White,
        )
        if (position == IconPosition.RIGHT){
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}