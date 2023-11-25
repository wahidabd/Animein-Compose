package com.wahidabd.animeku.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wahidabd.library.utils.common.emptyString


/**
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun RoundedIcon(
    size: Dp = 52.dp,
    icon: Int,
    iconSize: Dp = 32.dp,
    iconTint: Color = Color.White,
    background: Color = Color.White,
    title: String? = emptyString(),
    navigation: (() -> Unit)? = null
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(50.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color.LightGray)
            ) { navigation?.invoke() }
    ) {
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
        )
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(background)
        )

        Icon(
            painter = painterResource(id = icon),
            tint = iconTint,
            modifier = Modifier.size(iconSize),
            contentDescription = title,
        )
    }
}