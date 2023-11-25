package com.wahidabd.animeku.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.wahidabd.animeku.domain.anime.model.Genre


/**
 * Created by wahid on 11/19/2023.
 * Github github.com/wahidabd.
 */


@Composable
fun GenreList(
    genres: List<Genre>,
    expanded: Boolean
) {
    if (expanded) {
        FlowRow(
            modifier = Modifier.padding(horizontal = 10.dp),
            lastLineMainAxisAlignment = FlowMainAxisAlignment.Start,
            mainAxisSpacing = 4.dp,
            crossAxisSpacing = 4.dp
        ) {
            genres.forEach { genre ->
                AnimeGenreChip(text = genre.title.toString())
            }
        }
    } else {
        LazyRow(
            contentPadding = PaddingValues(
                horizontal = 10.dp,
                vertical = 4.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            this.items(genres) { genre ->
                AnimeGenreChip(text = genre.title.toString())
            }
        }
    }
}