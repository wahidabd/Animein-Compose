package com.wahidabd.animein.ui.components.lottie

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wahidabd.animein.R


/**
 * Created by Wahid on 7/15/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun LottieLoading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        LottieLoader(lottieFile = R.raw.loader, modifier = Modifier.size(250.dp))
    }
}

@Composable
fun LottieEmpty() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        LottieLoader(lottieFile = R.raw.empty, modifier = Modifier.size(250.dp))
    }
}

@Composable
fun LottieSearch() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        LottieLoader(lottieFile = R.raw.search, modifier = Modifier.size(250.dp))
    }
}

@Composable
fun LottieError() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ){
        LottieLoader(lottieFile = R.raw.error, modifier = Modifier.size(250.dp))
    }
}