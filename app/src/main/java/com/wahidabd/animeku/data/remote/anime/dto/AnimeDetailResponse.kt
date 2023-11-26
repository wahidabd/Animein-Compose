package com.wahidabd.animeku.data.remote.anime.dto


/**
 * Created by wahid on 11/19/2023.
 * Github github.com/wahidabd.
 */


data class AnimeDetailResponse(
    val id: Int? = 0,
    val slug: String? = null,
    val poster: String? = null,
    val title: String? = null,
    val type: String? = null,
    val status: String? = null,
    val duration: String? = null,
    val season: String? = null,
    val rating: String? = null,
    val synopsis: String? = null,
    val totalEpisode: String? = null,
    val releaseDate: String? = null,
    val studio: String? = null,
    val genres: List<GenreResponse>? = null
)