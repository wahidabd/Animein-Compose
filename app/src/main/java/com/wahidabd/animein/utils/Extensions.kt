package com.wahidabd.animein.utils

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.util.lerp
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.TimeUnit
import kotlin.math.absoluteValue


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun <T> StateFlow<Resource<T>>.collectStateFlow(
    onLoading: @Composable (() -> Unit),
    onEmpty: @Composable (() -> Unit)? = null,
    onFailure: @Composable (Throwable?, String?) -> Unit?,
    onSuccess: @Composable (data: T) -> Unit,
) {
    this.collectAsState().value.also {
        when (it) {
            is Resource.Default -> {}
            is Resource.Loading -> {
                onLoading.invoke()
            }

            is Resource.Empty -> {
                onEmpty?.invoke()
            }

            is Resource.Failure -> {
                onFailure.invoke(it.throwable, it.message)
            }

            is Resource.Success -> {
                onSuccess.invoke(it.data)
            }
        }
    }
}

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

fun String.replaceFullSlug() =
    this.replaceAfter("/episode", "")
        .replace("/episode", "")
        .replace("${Constant.BASE_URL}anime/", "")

fun String.replaceSynopsis() =
    this.replaceAfter("Catatan:", "")
        .replace("Catatan:", "")

fun String.fullTrim() = trim().replace("\uFEFF", "")

fun Context.showToast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()