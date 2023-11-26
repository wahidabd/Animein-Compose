package com.wahidabd.animeku.ui.component

import androidx.compose.material3.Text
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.wahidabd.animeku.ui.theme.ColorSecondary
import com.wahidabd.animeku.ui.theme.ColorWhite


/**
 * Created by wahid on 11/25/2023.
 * Github github.com/wahidabd.
 */

@Composable
fun AnimeDialog(
    message: String,
    confirmText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        text = {
            Text(
                text = message,
                color = ColorWhite,
                style = MaterialTheme.typography.titleMedium
            )
        },
        dismissButton = {
            TextButton(
                onClick = { onDismiss() }
            ) {
                Text(
                    text = "Dismiss",
                    color = ColorWhite,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        },
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(
                onClick = { onConfirm() }
            ) {
                Text(
                    text = confirmText,
                    color = ColorWhite,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        },
        containerColor = ColorSecondary
    )
}