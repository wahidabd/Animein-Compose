package com.wahidabd.animein.domain.anime.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Created by Wahid on 7/17/2023.
 * Github github.com/wahidabd.
 */


@Parcelize
data class Episode(
    val slug: String,
    val name: String
): Parcelable
