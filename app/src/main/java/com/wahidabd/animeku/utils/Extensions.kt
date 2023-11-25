package com.wahidabd.animeku.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import com.skydoves.landscapist.ShimmerParams
import com.wahidabd.animeku.ui.theme.ColorDarkGray
import com.wahidabd.animeku.ui.theme.ColorPrimary
import com.wahidabd.animeku.ui.theme.ColorTranslucentBlack
import java.util.concurrent.TimeUnit
import kotlin.math.absoluteValue


/**
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */

val gradientVerticalBrush = Brush.verticalGradient(listOf(Color.Transparent, ColorTranslucentBlack))

fun shimmerParams() = ShimmerParams(
    baseColor = ColorPrimary,
    highlightColor = ColorDarkGray,
    durationMillis = 500,
    dropOff = 0.65F,
    tilt = 20F
)

@OptIn(ExperimentalFoundationApi::class)
fun Modifier.carouselTransition(page: Int, pagerState: PagerState) =
    graphicsLayer {
        val pageOffset =
            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
        val transformation = lerp(
            start = 0.8f,
            stop = 1f,
            fraction = 1f - pageOffset.coerceIn(0f, 1f)
        )
        alpha = transformation
        scaleY = transformation
    }


fun Context.findComponentActivity(): ComponentActivity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is ComponentActivity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}

fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("no activity")
}


fun Long.formatMinSec(): String {
    return if (this == 0L) {
        "..."
    } else {
        String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(this),
            TimeUnit.MILLISECONDS.toSeconds(this) -
                    TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(this)
                    )
        )
    }
}

fun String.fullTrim() = trim().replace("\uFEFF", "")