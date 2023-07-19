package com.wahidabd.animein.ui.components.player

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


/**
 * Created by Wahid on 7/18/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun PlayerTopControl(
    title: String,
    onBackButton: () -> Unit,
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = {onBackButton.invoke()},
        ) {
            Icon(imageVector = Icons.Outlined.ChevronLeft, contentDescription = "", tint = Color.White)
        }
    }

}

@Preview
@Composable
fun PlayerTopControlPreview() {
    PlayerTopControl(
        "One Piece",
        onBackButton = {}
    )
}