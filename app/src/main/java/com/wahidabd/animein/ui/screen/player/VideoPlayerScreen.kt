package com.wahidabd.animein.ui.screen.player

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util
import com.ramcosta.composedestinations.annotation.Destination


/**
 * Created by Wahid on 7/10/2023.
 * Github github.com/wahidabd.
 */


@Destination
@Composable
fun VideoPlayerScreen() {

//    val url = "https://pixeldrain.com/api/file/YJmppqsz?download"
    val url = "https://desudrive.com/link/?id=eVYzczJkMWJLV2tCMjFLSkljL2tWRTlxbVZRYndONVBjYXE4c200SUc4dUJyVVRaV2JSalBqWTMxZEFraWZ0aTh1TWlabWVWYzVuSzNlWFhjVlBlV1BQRWxoV2Y2TCtsSE1HMUp1dTEvZFgwS0JIZnhtWDk0WTRQT1JCTWNiSDE1N3FvZ0Y0RGlTdUVpNDRNUFNtMU9sST0="
//    val url = "https://s9download.krakenfiles.com/force-download/ZjVhOGVmZjQ4MjA3NzY1ZPPCmXb84Rb3NC0w8Z_DaNZVGAIDQihDftet2_UvyLpk/0KhdODRVUK"
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Declaring ExoPlayer
//        val dataMediaSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()
//        val mediaSource = ProgressiveMediaSource.Factory(dataMediaSourceFactory)
//            .createMediaSource(MediaItem.fromUri(url))


        val mExoPlayer = remember(context) {
            ExoPlayer.Builder(context).build().apply {
//                val dataSourceFactory = DefaultDataSourceFactory(
//                    context,
//                    Util.getUserAgent(context, context.packageName)
//                )
                val dataMediaSourceFactory: DataSource.Factory = DefaultHttpDataSource.Factory()
                val video = ProgressiveMediaSource
                    .Factory(dataMediaSourceFactory)
                    .createMediaSource(MediaItem.fromUri(url))
                this.setMediaSource(video)
                this.prepare()
            }
        }

        // Implementing ExoPlayer
        AndroidView(factory = { context ->
            PlayerView(context).apply {
                player = mExoPlayer
            }
        })
    }

}