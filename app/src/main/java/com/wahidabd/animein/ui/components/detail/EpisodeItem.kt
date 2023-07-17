package com.wahidabd.animein.ui.components.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wahidabd.animein.R
import com.wahidabd.animein.domain.anime.model.Episode
import com.wahidabd.animein.ui.components.utils.RoundedIcon
import com.wahidabd.animein.ui.theme.ColorSecondary


/**
 * Created by Wahid on 7/17/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun EpisodeItem(
    episode: Episode,
    modifier: Modifier = Modifier,
    onclick: () -> Unit,
    onDownload: (String) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(6.dp))
            .background(ColorSecondary)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color.DarkGray)
            ) {
                onclick()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = episode.name,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier.padding(all = 10.dp)
        )

        IconButton(
            onClick = { onDownload(episode.slug) },
            modifier = Modifier
                .size(32.dp)
                .padding(6.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_downlaod),
                tint = Color.White.copy(alpha = 0.55F),
                contentDescription = "Download"
            )
        }
    }
}

@Preview
@Composable
fun EpisodeItemPreview() {
    EpisodeItem(
        episode = Episode("", "Episode 1"),
        onclick = {},
        onDownload = {}
    )
}