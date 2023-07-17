package com.wahidabd.animein.ui.components.utils

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wahidabd.animein.ui.theme.ColorPrimary
import com.wahidabd.animein.ui.theme.ColorSecondary


/**
 * Created by Wahid on 7/17/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun FloatingBackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    FloatingActionButton(
        onClick = { onClick() },
        modifier = modifier.size(24.dp),
        contentColor = ColorSecondary,
        containerColor = ColorPrimary
    ) {
        Icon(
            imageVector = Icons.Outlined.ArrowBack,
            contentDescription = null
        )
    }
}