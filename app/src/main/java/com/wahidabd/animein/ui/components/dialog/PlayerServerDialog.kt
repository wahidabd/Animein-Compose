package com.wahidabd.animein.ui.components.dialog

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.wahidabd.animein.domain.player.domain.PlayerSource
import com.wahidabd.animein.screen.player.PlayerViewModel
import com.wahidabd.animein.ui.theme.ColorOrange
import com.wahidabd.animein.ui.theme.ColorPrimary
import com.wahidabd.animein.ui.theme.ColorSecondary


/**
 * Created by Wahid on 7/20/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun PlayerServerDialog(
    sources: List<PlayerSource>,
    onclick: (url: String) -> Unit,
    onDismiss: (Boolean) -> Unit,
) {

    Dialog(onDismissRequest = { onDismiss(false) }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(ColorPrimary)
                .padding(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Select Server: ",
                style = MaterialTheme.typography.titleSmall,
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 14.sp,
            )

            Divider(color = Color.DarkGray, modifier = Modifier.padding(top = 4.dp))

            LazyColumn(
                contentPadding = PaddingValues(vertical = 6.dp)
            ) {
                items(sources) {source ->
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(ColorSecondary.copy(alpha = 0.56F))
                            .padding(vertical = 6.dp)
                            .fillMaxWidth()
                            .clickable { onclick(source.url) },
                    ){
                        Text(
                            text = "${source.server} - (${source.resolution})",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.White.copy(alpha = 0.8f),
                            modifier = Modifier.padding(horizontal = 6.dp),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                }
            }
        }
    }
}
