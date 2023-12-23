package com.wahidabd.animeku.ui.component.more

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import com.wahidabd.animeku.R
import com.wahidabd.animeku.ui.theme.ColorWhite


/**
 * Created by wahid on 12/13/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun AccountLogin(
    navigator: DestinationsNavigator
) {
    Row(
        modifier = Modifier
            .padding(vertical = 12.dp, horizontal = 16.dp)
            .fillMaxWidth(),
    ) {
        AsyncImage(
            modifier = Modifier
                .height(52.dp)
                .width(52.dp)
                .clip(RoundedCornerShape(50.dp)),
            model = R.drawable.img_sample,
            contentDescription = "image profile",
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = "Wahid",
                style = MaterialTheme.typography.bodyMedium,
                color = ColorWhite
            )

            Text(
                text = "wahid@mail.com",
                style = MaterialTheme.typography.bodySmall,
                color = ColorWhite
            )
        }

//        Icon(
//            painter = painterResource(id = R.drawable.ic_arrow_right),
//            contentDescription = "navigation",
//            tint = Color.White,
//            modifier = Modifier
//                .align(Alignment.CenterVertically)
//                .size(24.dp)
//        )
    }
}

@Preview
@Composable
fun AccountLoginScreenPreview() {
    AccountLogin(navigator = EmptyDestinationsNavigator)
}

