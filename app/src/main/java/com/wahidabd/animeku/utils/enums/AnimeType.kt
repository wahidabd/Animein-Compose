package com.wahidabd.animeku.utils.enums

import com.wahidabd.animeku.utils.constants.Constants
import com.wahidabd.animeku.utils.constants.Endpoints


/**
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */


enum class AnimeType(val label: String, val query: String? = null) {
    ONGOING("Ongoing", Endpoints.ONGOING),
    FINISHED("Finished", Endpoints.FINISH),
    MOVIE("Movie", Endpoints.MOVIE),
    POPULAR("Popular", Endpoints.POPULAR),
    SPOTLIGHT("Spotlight")
}