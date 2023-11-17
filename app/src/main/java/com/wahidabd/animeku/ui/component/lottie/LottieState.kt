package com.wahidabd.animeku.ui.component.lottie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.wahidabd.animeku.R


/**
 * Created by wahid on 11/17/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun LottieLoading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        LottieLoader(lottieFile = R.raw.loading, modifier = Modifier.size(250.dp))
    }
}

@Composable
fun LottieEmpty() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        LottieLoader(lottieFile = R.raw.empty, modifier = Modifier.size(250.dp))
    }
}

@Composable
fun LottieSearch() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        LottieLoader(lottieFile = R.raw.search, modifier = Modifier.size(250.dp))
    }
}

@Composable
fun LottieError(error: String?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieLoader(lottieFile = R.raw.error, modifier = Modifier.size(250.dp))
            Text(
                text = error.toString(),
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }
}