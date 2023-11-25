package com.wahidabd.animeku.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wahidabd.animeku.R
import com.wahidabd.animeku.ui.theme.ColorPrimary


/**
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun HeaderBackButton(
    modifier: Modifier = Modifier,
    title: String? = null,
    color: Color = ColorPrimary,
    onBackClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .background(color)
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RoundedIcon(
            icon = R.drawable.ic_arrow_left,
            background = ColorPrimary,
            size = 32.dp,
            iconSize = 24.dp,
            navigation = onBackClick
        )

        Spacer(modifier = Modifier.width(12.dp))

        if (title != null && color != Color.Transparent) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}