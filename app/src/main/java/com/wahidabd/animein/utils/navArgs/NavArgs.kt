package com.wahidabd.animein.utils.navArgs

import com.wahidabd.animein.utils.enums.AnimeType


/**
 * Created by Wahid on 7/16/2023.
 * Github github.com/wahidabd.
 */



data class AnimePagingArgs(
    val type: AnimeType = AnimeType.ONGOING
)