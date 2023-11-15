package com.wahidabd.animein.screen.player

import android.content.Context
import android.net.Uri
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.Player
import androidx.media3.common.util.Util
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.extractor.ExtractorsFactory
import com.wahidabd.animein.utils.Constant


/**
 * Created by Wahid on 7/19/2023.
 * Github github.com/wahidabd.
 */

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
fun exoplayerBuilder(url: String, context: Context): ExoPlayer {


    return ExoPlayer.Builder(context)
        .setSeekBackIncrementMs(Constant.PLAYER_SEEK_BACK_INCREMENT)
        .setSeekForwardIncrementMs(Constant.PLAYER_SEEK_FORWARD_INCREMENT)
        .build().apply {

            val mediaItem = MediaItem.Builder()
                .setUri(Uri.parse(url))
                .setMimeType(MimeTypes.APPLICATION_MP4)
                .build()
            val sourceFactory = DefaultDataSource.Factory(context)
            val dataSourceFactory = DefaultDataSource.Factory(
                context, sourceFactory
            )

            val source = ProgressiveMediaSource
                .Factory(dataSourceFactory)
                .createMediaSource(mediaItem)

            setMediaSource(source)
            prepare()
            playWhenReady = true

            videoScalingMode = C.VIDEO_SCALING_MODE_SCALE_TO_FIT
            repeatMode = Player.REPEAT_MODE_ONE
        }
}