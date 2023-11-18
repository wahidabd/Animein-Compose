package com.wahidabd.animeku.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.wahidabd.animeku.utils.enums.AnimeType


/**
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun TitleSection(
    type: AnimeType,
    navigator: DestinationsNavigator? = EmptyDestinationsNavigator
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .clickable(
                enabled = type.query?.isNotBlank() == true,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(color = Color.LightGray)
            ){},
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = type.label,
            color = Color.White,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier
                .padding(all = 4.dp)
                .padding(top = 4.dp, bottom = 4.dp)
        )

        if (type.query?.isNotEmpty() == true) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                tint = Color.White,
                modifier = Modifier.size(24.dp),
                contentDescription = "Arrow"
            )
        }
    }
}