package com.wahidabd.animein.screen.player

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.wahidabd.animein.ui.theme.AnimeinTheme
import com.wahidabd.animein.ui.theme.ColorPrimary


class PlayerActivity : ComponentActivity() {

    companion object {
        fun start(context: Context){
            context.startActivity(Intent(context, PlayerActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimeinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = ColorPrimary
                ) {
                    VideoPlayerScreen()
                }
            }
        }
    }

    @androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
    @Composable
    fun VideoPlayerScreen() {

        val context = LocalContext.current
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
        exoPlayer.videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT
        exoPlayer.repeatMode = Player.REPEAT_MODE_OFF

        DisposableEffect(key1 = Unit) {
            onDispose { exoPlayer.release() }
        }

        Box(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            AndroidView(
                factory = {
                    PlayerView(context).apply {
                        showController()
                        useController = true

                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                        player = exoPlayer
                        layoutParams = FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                    }
                }
            )
        }
    }
}
