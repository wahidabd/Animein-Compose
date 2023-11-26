package com.wahidabd.animeku.domain.anime.model

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by wahid on 11/19/2023.
 * Github github.com/wahidabd.
 */


data class AnimeDetail(
    val slug: String? = emptyString(),
    val poster: String? = emptyString(),
    val title: String? = emptyString(),
    val type: String? = emptyString(),
    val status: String? = emptyString(),
    val duration: String? = emptyString(),
    val season: String? = emptyString(),
    val rating: String? = emptyString(),
    val synopsis: String? = emptyString(),
    val totalEpisode: String? = emptyString(),
    val releaseDate: String? = emptyString(),
    val studio: String? = emptyString(),
    val genres: List<Genre>? = emptyList(),
    var bookmark: Boolean? = false
)
