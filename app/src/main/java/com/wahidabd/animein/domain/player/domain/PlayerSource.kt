package com.wahidabd.animein.domain.player.domain

import com.wahidabd.library.utils.common.emptyString


/**
 * Created by Wahid on 7/20/2023.
 * Github github.com/wahidabd.
 */


data class PlayerSource(
    val url: String = emptyString(),
    val resolution: String = emptyString(),
    val server: String = emptyString()
)
