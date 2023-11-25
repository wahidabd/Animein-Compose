package com.wahidabd.animeku.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wahidabd.animeku.R
import com.wahidabd.animeku.domain.anime.model.Episode
import com.wahidabd.animeku.ui.theme.ColorSecondary


/**
 * Created by wahid on 11/20/2023.
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
            text = episode.title ?: "",
            style = MaterialTheme.typography.bodySmall,
            fontSize = 12.sp,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(all = 10.dp)
        )

//        IconButton(
//            onClick = { onDownload(episode.slug.toString()) },
//            modifier = Modifier
//                .size(32.dp)
//                .padding(6.dp)
//        ) {
//            Icon(
//                painter = painterResource(id = R.drawable.ic_downlaod),
//                tint = Color.White.copy(alpha = 0.55F),
//                contentDescription = "Download"
//            )
//        }
    }
}