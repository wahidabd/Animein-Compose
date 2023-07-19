package com.wahidabd.animein.ui.components.player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.IconButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.wahidabd.animein.R


/**
 * Created by Wahid on 7/18/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun PlayerCenterControl(
    modifier: Modifier = Modifier,
    isPlaying: () -> Boolean,
    onBackward: () -> Unit,
    onPause: () -> Unit,
    onForward: () -> Unit
) {

    val isVideoPlaying = remember(isPlaying()) { isPlaying() }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(onClick = { onBackward() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_backward),
                tint = Color.White,
                contentDescription = "Backward 5 second",
            )
        }

        IconButton(onClick = { onPause() }) {
            Icon(
                painter = painterResource(
                    if (isVideoPlaying) R.drawable.ic_pause
                    else R.drawable.ic_play
                ),
                tint = Color.White,
                contentDescription = "Pause or Play",
            )
        }

        IconButton(onClick = { onForward() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_forward),
                tint = Color.White,
                contentDescription = "Backward 5 second",
            )
        }
    }
}

@Preview
@Composable
fun PlayerCenterControlPreview() {
    PlayerCenterControl(
        isPlaying = { true },
        onBackward = {},
        onForward = {},
        onPause = {}
    )
}