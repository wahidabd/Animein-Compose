package com.wahidabd.animeku.domain.anime.model

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by wahid on 11/19/2023.
 * Github github.com/wahidabd.
 */


data class Genre(
    val slug: String? = emptyString(),
    val title: String? = emptyString()
)