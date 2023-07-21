package com.wahidabd.animein.screen.player

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.wahidabd.animein.ui.theme.AnimeinTheme
import com.wahidabd.animein.ui.theme.ColorPrimary
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.extensions.debug
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PlayerActivity : ComponentActivity() {

    companion object {
        const val CONTENT_URL = ""
        fun start(context: Context, contentUrl: String) {
            context.startActivity(Intent(context, PlayerActivity::class.java).apply {
                this.putExtra(CONTENT_URL, contentUrl)
            })
        }
    }

    private var contentUrl = emptyString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        contentUrl = intent.getStringExtra(CONTENT_URL).toString()
        debug { "CONTENT URL -> $contentUrl" }

        setContent {
            AnimeinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = ColorPrimary
                ) {
                    VideoPlayerScreen(contentUrl)
                }
            }
        }
    }
}
