package com.wahidabd.animein.ui.components.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wahidabd.animein.ui.theme.ColorDarkGray
import com.wahidabd.animein.ui.theme.ColorOrange
import com.wahidabd.animein.ui.theme.Shapes


/**
 * Created by Wahid on 7/16/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun TextRectangleOrange(
    text: String,
    star: Boolean = false,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(Shapes.small)
            .background(ColorOrange)
            .padding(all = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (star) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .size(16.dp)
                        .padding(end = 4.dp)
                )
            }
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall,
                fontSize = 10.sp,
                color = Color.White
            )
        }
    }
}

@Composable
fun TextRectangleDarkGray(
    text: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .background(ColorDarkGray)
            .padding(vertical = 1.dp, horizontal = 8.dp)
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall,
            fontSize = 8.sp,
            color = Color.White
        )
    }
}

@Preview
@Composable
fun TextRectangleOrangePreview() {
    Column {
        TextRectangleOrange(text = "Ep 15 / 26", true)
        Row {
            TextRectangleDarkGray(text = "TV")
            TextRectangleDarkGray(text = "HD")
        }
    }
}