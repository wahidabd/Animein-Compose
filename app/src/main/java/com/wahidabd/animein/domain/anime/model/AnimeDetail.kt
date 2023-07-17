package com.wahidabd.animein.domain.anime.model

import android.os.Parcelable
import com.wahidabd.library.utils.common.emptyString
import kotlinx.parcelize.Parcelize


/**
 * Created by Wahid on 7/17/2023.
 * Github github.com/wahidabd.
 */


@Parcelize
data class AnimeDetail(
    val title: String? = emptyString(),
    val poster: String? = emptyString(),
    val backdrop: String? = emptyString(),
    val type: String? = emptyString(),
    val resolution: String? = emptyString(),
    val releaseEndDate: String? = emptyString(),
    val rating: String? = emptyString(),
    val totalEpisode: String? = emptyString(),
    val synopsis: String? = emptyString(),
    val alternative: String? = emptyString(),
    val episode: Episode? = null,
    val genres: List<Genre>? = null
): Parcelable