package com.wahidabd.animeku.domain.anime.model

import com.wahidabd.animeku.data.anime.dto.AnimeResponse
import com.wahidabd.library.utils.common.emptyString


/**
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */


data class Anime(
    val slug: String? = emptyString(),
    val poster: String? = emptyString(),
    val title: String? = emptyString(),
    val type: String? = emptyString(),
    val rating: String? = emptyString(),
    val status: String? = emptyString()
)

fun AnimeResponse.toDomain(): Anime =
    Anime(
        slug = slug,
        poster = poster,
        title = title,
        type = type,
        rating = rating,
        status = status
    )
