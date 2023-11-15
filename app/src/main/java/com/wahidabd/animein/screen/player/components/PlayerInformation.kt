package com.wahidabd.animein.screen.player.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowCircleDown
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 7/19/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun PlayerInformation(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Watashi no Shiawase na Kekkon",
            style = MaterialTheme.typography.headlineSmall,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Clip,
            modifier = Modifier.padding(vertical = 10.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconTextInformation(
                icon = Icons.Outlined.ChatBubbleOutline,
                text = "Comments",
                onclick = {}
            )
            IconTextInformation(
                icon = Icons.Outlined.ChatBubbleOutline,
                text = "Resolution",
                onclick = {}
            )
            IconTextInformation(
                icon = Icons.Outlined.ArrowCircleDown,
                text = "Download",
                onclick = {}
            )
            IconTextInformation(
                icon = Icons.Outlined.Info,
                text = "Info",
                onclick = {}
            )
        }
    }
}

@Composable
fun IconTextInformation(
    icon: ImageVector,
    text: String,
    onclick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = emptyString(),
            tint = Color.White,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 12.sp,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Clip,
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}

@Preview
@Composable
fun PlayerInformationPreview() {
    PlayerInformation()
}