package com.wahidabd.animein.screen.player

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE
import android.content.res.Configuration
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.Player.STATE_ENDED
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.media3.ui.PlayerView.SHOW_BUFFERING_WHEN_PLAYING
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.wahidabd.animein.ui.components.player.PlayerControl
import com.wahidabd.animein.utils.Constant
import com.wahidabd.animein.utils.collectStateFlow
import com.wahidabd.animein.utils.findComponentActivity
import com.wahidabd.library.utils.common.emptyString
import kotlinx.coroutines.delay


/**
 * Created by Wahid on 7/10/2023.
 * Github github.com/wahidabd.
 */


@SuppressLint("RememberReturnType")
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
@Destination
@Composable
fun VideoPlayerScreen(
    contentUrl: String,
    viewModel: PlayerViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val systemUiController = rememberSystemUiController()
    val activity = context.findComponentActivity()!!
    val enterFullScreen = { activity.requestedOrientation = SCREEN_ORIENTATION_USER_LANDSCAPE }
    val exitFullScreen = { activity.requestedOrientation = SCREEN_ORIENTATION_UNSPECIFIED }
    val url = remember { mutableStateOf(emptyString()) }


    // control
    var shouldShowControl by remember { mutableStateOf(false) }
    var duration by remember { mutableLongStateOf(0L) }
    var timer by remember { mutableLongStateOf(0L) }
    var bufferedPercentage by remember { mutableIntStateOf(0) }
    var isPlaying by remember { mutableStateOf(false) }
    var currentTime by remember { mutableLongStateOf(0L) }



    LaunchedEffect(Unit){
        viewModel.player(contentUrl)
    }

    viewModel.player.collectStateFlow(
        onLoading = {},
        onFailure = {_, _ -> },
        onSuccess = {
            url.value = if (it.isNotEmpty()) it[0].url else ""
        }
    )

    val exoPlayer = remember { exoplayerBuilder(url = url.value, context = context) }

    LaunchedEffect(key1 = shouldShowControl) {
        if (shouldShowControl) {
            delay(Constant.PLAYER_CONTROLS_VISIBILITY)
            shouldShowControl = false
        }
    }

    DisposableEffect(exoPlayer) {
        val listener = object : Player.Listener {
            override fun onEvents(player: Player, events: Player.Events) {
                super.onEvents(player, events)
                isPlaying = player.isPlaying
                duration = if (player.duration > 0) player.duration else 0L
                timer = player.contentPosition
                bufferedPercentage = player.bufferedPercentage
                // minus playback
            }

            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                super.onMediaItemTransition(mediaItem, reason)
                // onTrailerChange and title
            }
        }
        exoPlayer.addListener(listener)
        onDispose {
            exoPlayer.removeListener(listener)
            exoPlayer.release()
        }
    }

    SideEffect {
        systemUiController.isStatusBarVisible = !isLandscape
        systemUiController.isNavigationBarVisible = !isLandscape
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    useController = false
                    player = exoPlayer
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    setShowBuffering(SHOW_BUFFERING_WHEN_PLAYING)
                }
            },
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(color = Color.DarkGray)
                ) {
                    shouldShowControl = shouldShowControl.not()
                }
        )

        PlayerControl(
            isVisible = { shouldShowControl },
            isPlaying = { isPlaying },
            onBackward = { exoPlayer.seekBack() },
            onForward = { exoPlayer.seekForward() },
            totalDuration = { duration },
            currentTime = { timer },
            bufferPercentage = { bufferedPercentage },
            onSeekChanged = { position -> exoPlayer.seekTo(position.toLong()) },
            onSettingClick = {},
            onBackButton = { activity.onBackPressedDispatcher.onBackPressed() },
            onPause = {
                when {
                    exoPlayer.isPlaying -> {
                        exoPlayer.pause()
                    }

                    exoPlayer.isPlaying.not() && exoPlayer.playbackState == STATE_ENDED -> {
                        exoPlayer.seekTo(0, 0)
                        exoPlayer.playWhenReady = true
                    }

                    else -> {
                        exoPlayer.play()
                    }
                }
                isPlaying = isPlaying.not()
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}