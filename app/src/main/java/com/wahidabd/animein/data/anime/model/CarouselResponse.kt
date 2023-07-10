package com.wahidabd.animein.data.anime.model

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 7/10/2023.
 * Github github.com/wahidabd.
 */


data class CarouselResponse(
    val slug: String? = emptyString(),
    val title: String? = emptyString(),
    val image: String? = emptyString(),
    val type: String? = emptyString(),
    val rating: String? = emptyString(),
    val episode: String? = emptyString()
)
