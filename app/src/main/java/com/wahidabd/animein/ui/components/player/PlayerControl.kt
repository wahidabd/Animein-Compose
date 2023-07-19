package com.wahidabd.animein.ui.components.player

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


/**
 * Created by Wahid on 7/18/2023.
 * Github github.com/wahidabd.
 */


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PlayerControl(
    modifier: Modifier = Modifier,
    isVisible: () -> Boolean,
    isPlaying: () -> Boolean,
    onBackward: () -> Unit,
    onPause: () -> Unit,
    onForward: () -> Unit,
    totalDuration: () -> Long,
    currentTime: () -> Long,
    bufferPercentage: () -> Int,
    isLandscape: () -> Boolean,
    onLandscape: () -> Unit,
    onSeekChanged: (timeMs: Float) -> Unit
) {
    val visible = remember(isVisible()) { isVisible() }

    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier.background(Color.Black.copy(alpha = 0.6F))
        ) {
            PlayerTopControl(
                title = "",
                onBackButton = {},
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .animateEnterExit(
                        enter = slideInVertically(
                            initialOffsetY = { it }
                        ),
                        exit = slideOutVertically(
                            targetOffsetY = { it }
                        )
                    )
            )

            PlayerCenterControl(
                isPlaying = isPlaying,
                onBackward = onBackward,
                onPause = onPause,
                onForward = onForward,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
            )

            PlayerBottomControl(
                totalDuration = totalDuration,
                currentTime = currentTime,
                bufferPercentage = bufferPercentage,
                isLandscape = isLandscape,
                onLandscape = onLandscape,
                onSeekChanged = onSeekChanged,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .animateEnterExit(
                        enter = slideInVertically(
                            initialOffsetY = { it }
                        ),
                        exit = slideOutVertically(
                            targetOffsetY = { it }
                        )
                    )
            )
        }
    }
}

fun Modifier.setScreenOrientation(isLandscape: Boolean) =
    if (!isLandscape) {
        this
            .fillMaxWidth()
            .aspectRatio(16F / 9F)
    } else {
        this.fillMaxSize()
    }


@Preview
@Composable
fun PlayerControlPreview() {
    PlayerControl(
        totalDuration = { 10000 },
        currentTime = { 1000 },
        bufferPercentage = { 500 },
        onSeekChanged = { 100F },
        isLandscape = { false },
        onLandscape = {},
        isPlaying = { true },
        onBackward = {},
        onForward = {},
        onPause = {},
        isVisible = { true },
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16F / 9F)
    )
}