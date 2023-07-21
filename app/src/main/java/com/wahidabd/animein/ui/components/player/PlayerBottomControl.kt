package com.wahidabd.animein.ui.components.player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.wahidabd.animein.R
import com.wahidabd.animein.ui.theme.ColorOrange
import com.wahidabd.animein.utils.formatMinSec


/**
 * Created by Wahid on 7/18/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun PlayerBottomControl(
    modifier: Modifier = Modifier,
    totalDuration: () -> Long,
    currentTime: () -> Long,
    bufferPercentage: () -> Int,
    onclick: () -> Unit,
    onSeekChanged: (timeMs: Float) -> Unit
) {

    val duration = remember(totalDuration()) { totalDuration() }
    val videoTime = remember(currentTime()) { currentTime() }
    val buffer = remember(bufferPercentage()) { bufferPercentage() }


    Column(modifier = modifier) {

        PlayerDuration(duration = duration, onclick = { onclick() })

        Box(modifier = Modifier.fillMaxWidth()) {
            Slider(
                value = buffer.toFloat(),
                enabled = false,
                onValueChange = { },
                valueRange = 0f..100f,
                colors =
                SliderDefaults.colors(
                    disabledThumbColor = Color.Transparent,
                    disabledActiveTrackColor = Color.LightGray
                )
            )

            Slider(
                value = videoTime.toFloat(),
                onValueChange = { onSeekChanged.invoke(it) },
                valueRange = 0f..duration.toFloat(),
                colors = SliderDefaults.colors(
                    thumbColor = Color.White,
                    activeTickColor = Color.DarkGray,
                    activeTrackColor = ColorOrange
                )
            )
        }
    }
}

@Composable
private fun PlayerDuration(
    duration: Long,
    onclick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = duration.formatMinSec(),
            color = Color.White
        )

        IconButton(onClick = { onclick() }, modifier = Modifier.padding(horizontal = 16.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_settings),
                tint = Color.White,
                contentDescription = "Backward 5 second",
            )
        }
    }
}

@Preview
@Composable
fun PlayerBottomControlPreview() {
    PlayerBottomControl(
        totalDuration = { 10000 },
        currentTime = { 1000 },
        bufferPercentage = { 500 },
        onSeekChanged = { 100F },
        onclick = {}
    )
}