package com.wahidabd.animein.screen.player

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
import android.content.pm.ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE
import android.content.res.Configuration
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.wahidabd.animein.ui.theme.ColorPrimary
import com.wahidabd.animein.utils.findComponentActivity


/**
 * Created by Wahid on 7/10/2023.
 * Github github.com/wahidabd.
 */


@SuppressLint("RememberReturnType")
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
@Destination
@Composable
fun VideoPlayerScreen(
    navigator: DestinationsNavigator
) {

    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    val systemUiController = rememberSystemUiController()
    val activity = context.findComponentActivity()!!
    val enterFullScreen = { activity.requestedOrientation = SCREEN_ORIENTATION_USER_LANDSCAPE }
    val exitFullScreen = { activity.requestedOrientation = SCREEN_ORIENTATION_UNSPECIFIED }


    val url =
        "https://archive.org/download/AtCFIkRgEM_20220225/26/MP4/Kuramanime-KnY_BD-26_END-360p-Anitoki.mp4"
    val exoPlayer = remember {
        ExoPlayer.Builder(context)
            .build()
            .apply {
                val sourceFactory = DefaultDataSource.Factory(context)
                val dataSourceFactory = DefaultDataSource.Factory(
                    context, sourceFactory
                )
                val source = ProgressiveMediaSource
                    .Factory(dataSourceFactory)
                    .createMediaSource(MediaItem.fromUri(url))

                setMediaSource(source)
                prepare()
            }
    }


    exoPlayer.playWhenReady = true
    exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING
    exoPlayer.repeatMode = Player.REPEAT_MODE_ONE

    SideEffect {
        systemUiController.isStatusBarVisible = !isLandscape
        systemUiController.isNavigationBarVisible = !isLandscape
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ColorPrimary)
    ) {
        Box(
            modifier = if (!isLandscape) {
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(16F / 9F)
            } else {
                Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            },
            contentAlignment = Alignment.Center,
        ) {
            AndroidView(
                factory = {
                    PlayerView(context).apply {
                        player = exoPlayer
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                        layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    }
                }
            )


            /**
             * the button only can use in debugging mode
             * we're change in the future with custom controller
             */
            Button(
                modifier = Modifier.align(Alignment.BottomEnd),
                onClick = if (isLandscape) exitFullScreen else enterFullScreen
            ) {
                Text(text = if (isLandscape) "Exit Fullscreen" else "Enter Fullscreen")
            }
        }
    }

    val onBackPressedCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                exitFullScreen()
            }
        }
    }

    val onBackPressedDispatcher = activity.onBackPressedDispatcher
    DisposableEffect(onBackPressedDispatcher) {
        onBackPressedDispatcher.addCallback(onBackPressedCallback)
        onDispose {
            onBackPressedCallback.remove()
            exoPlayer.release()
        }
    }

    SideEffect {
        onBackPressedCallback.isEnabled = isLandscape
        if (isLandscape) {
            if (activity.requestedOrientation == SCREEN_ORIENTATION_UNSPECIFIED) {
                activity.requestedOrientation = SCREEN_ORIENTATION_USER_LANDSCAPE
            } else {
                activity.requestedOrientation = SCREEN_ORIENTATION_UNSPECIFIED
            }
        }
    }
}


@Preview
@Composable
fun VideoPlayerScreenPreview() {
    VideoPlayerScreen(EmptyDestinationsNavigator)
}