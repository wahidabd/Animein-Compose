package com.wahidabd.animeku.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wahidabd.animeku.domain.anime.model.Genre
import com.wahidabd.animeku.ui.theme.ColorSecondary


/**
 * Created by wahid on 11/19/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun AnimeGenreChip(
    genre: Genre,
    onclick: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(color = ColorSecondary, shape = RoundedCornerShape(50.dp))
            .clip(RoundedCornerShape(50.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color.DarkGray),
                onClick = { onclick.invoke() }
            )
    ) {
        Text(
            text = genre.title.toString(),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}