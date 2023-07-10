package com.wahidabd.animein.domain.anime.model

import com.wahidabd.animein.data.anime.model.CarouselResponse
import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


data class Carousel(
    val slug: String? = emptyString(),
    val title: String? = emptyString(),
    val image: String? = emptyString(),
    val type: String? = emptyString(),
    val rating: String? = emptyString(),
    val episode: String? = emptyString()
)

fun CarouselResponse.toDomain(): Carousel =
    Carousel(
        slug, title, image, type, rating, episode
    )