package com.wahidabd.animein.screen.player.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable


/**
 * Created by Wahid on 7/23/2023.
 * Github github.com/wahidabd.
 */


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlayerModalBottomSheet(
    onSelected: (url: String) -> Unit,
    onDismiss: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    
}