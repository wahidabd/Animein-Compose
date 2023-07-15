package com.wahidabd.animein.screen.player

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
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.ramcosta.composedestinations.annotation.Destination


/**
 * Created by Wahid on 7/10/2023.
 * Github github.com/wahidabd.
 */


@Destination
@Composable
fun VideoPlayerScreen() {

//    val url = "https://pixeldrain.com/api/file/YJmppqsz?download"
//    val url = "https://wibufile.com/videos/s1/I9pRdwn6ZTLikVV_1689182834.mp4"
//    val url = "https://1xmcgre8ic3kpmjnhktyyx9rn6k02d8nravehb4croki3djrmu.ctmp.space/files/9/1jfyrcieayu0se/Alqanime_SUOneesanHS2_08_360p.mp4"
    val url = "https://download1323.mediafire.com/w2s3zplyw4fgUrEsHfYOtZ0hFRH0OVGPFn1yfu8BK3pGyMWuy8AtuvIV26qsqPVxc82jaSVjkpLUQi8n7FfwnxAqrUjKf-eSyFUtj-hi5uoek5WMwS9yx27gbnjdS38fcK5Csxq27mbk9hgJUcTyDnzaSSmncZFN7-BrCc3QlMfmcg/fji0gaa1jssobr9/Alqanime_IsekaiVendingMachine_02_360p.mp4"
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val mExoPlayer = remember(context) {
            ExoPlayer.Builder(context).build().apply {
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