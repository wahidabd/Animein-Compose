package com.wahidabd.animein.domain.anime.model

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


data class Anime(
    val slug: String? = emptyString(),
    val poster: String? = emptyString(),
    val rating: String? = emptyString(),
    val episode: String? = emptyString(),
    val title: String? = emptyString(),
    val subtitle: String? = emptyString(),
    val season: String? = emptyString()
)
