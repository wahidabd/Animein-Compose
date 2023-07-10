package com.wahidabd.animein.ui.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wahidabd.animein.R
import com.wahidabd.animein.data.anime.model.AnimeResponse
import com.wahidabd.animein.ui.theme.ColorTranslucentBlack
import com.wahidabd.animein.ui.theme.Shapes


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun AnimeItem(
    data: AnimeResponse,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    val gradient = Brush.verticalGradient(listOf(Color.Transparent, ColorTranslucentBlack))

    Box(
        modifier = modifier
            .clip(Shapes.small)
    ) {
        AsyncImage(
            model = data.poster,
            contentDescription = data.title,
            placeholder = painterResource(id = R.drawable.img_sample),
            error = painterResource(id = R.drawable.ic_load_error),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(266.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(gradient)
                .align(Alignment.BottomStart)
                .padding(start = 8.dp, end = 8.dp, top = 24.dp, bottom = 12.dp)
        ) {

            Text(
                text = data.title.toString(),
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = data.subtitle.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 10.sp,
                    color = Color.LightGray
                )
                Text(
                    text = stringResource(id = R.string.label_bullet),
                    color = Color.LightGray,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                )
                Text(
                    text = data.season.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    fontSize = 10.sp,
                    maxLines = 1,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AnimeItemPreview() {
    AnimeItem(
        AnimeResponse(
            "",
            "",
            "8.9",
            "Ep 12",
            "One Piece",
            "Sub Indo",
            "Summer 2023",
        ),
        onClick = {}
    )
}