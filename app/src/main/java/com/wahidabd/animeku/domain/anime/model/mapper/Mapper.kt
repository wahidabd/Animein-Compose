package com.wahidabd.animeku.domain.anime.model.mapper

import com.wahidabd.animeku.data.local.anime.dto.AnimeEntity
import com.wahidabd.animeku.data.remote.anime.dto.AnimeDetailResponse
import com.wahidabd.animeku.data.remote.anime.dto.AnimeResponse
import com.wahidabd.animeku.data.remote.anime.dto.EpisodeResponse
import com.wahidabd.animeku.data.remote.anime.dto.GenreResponse
import com.wahidabd.animeku.domain.anime.model.Anime
import com.wahidabd.animeku.domain.anime.model.AnimeDetail
import com.wahidabd.animeku.domain.anime.model.Episode
import com.wahidabd.animeku.domain.anime.model.Genre


/**
 * Created by wahid on 11/19/2023.
 * Github github.com/wahidabd.
 */


fun AnimeResponse.toDomain(): Anime =
    Anime(
        slug = slug,
        poster = poster,
        title = title,
        type = type,
        rating = rating,
        status = status
    )

fun GenreResponse.toDomain(): Genre =
    Genre(
        slug = slug,
        title = title
    )

fun EpisodeResponse.toDomain(): Episode =
    Episode(
        slug = slug,
        title = title,
        number = number,
        date = date,
    )


fun AnimeEntity.toAnime(): Anime =
    Anime(
        slug = slug,
        poster = poster,
        title = title,
        type = type,
        rating = rating,
        status = status
    )

fun AnimeDetailResponse.toDomain(): AnimeDetail =
    AnimeDetail(
        slug = slug,
        poster = poster,
        title = title,
        type = type,
        status = status,
        duration = duration,
        season = season,
        rating = rating,
        synopsis = synopsis,
        totalEpisode = totalEpisode,
        releaseDate = releaseDate,
        studio = studio,
        genres = genres?.map { it.toDomain() },
        bookmark = false
    )

fun AnimeDetail.toEntity(): AnimeEntity =
    AnimeEntity(
        slug = slug ?: "",
        poster = poster,
        title = title,
        type = type,
        rating = rating,
        status = status,
        totalEpisode = totalEpisode,
        releaseDate = releaseDate,
        studio = studio,
        season = season,
        duration = duration,
        bookmark = bookmark
    )

fun AnimeEntity?.toDomain(): AnimeDetail =
    AnimeDetail(
        slug = this?.slug,
        poster = this?.poster,
        title = this?.title,
        type = this?.type,
        rating = this?.rating,
        status = this?.status,
        totalEpisode = this?.totalEpisode,
        releaseDate = this?.releaseDate,
        studio = this?.studio,
        season = this?.season,
        duration = this?.duration,
        bookmark = this?.bookmark
    )