package com.wahidabd.animein.utils.enums


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


enum class AnimeType (val title: String, val query: String? = null){
    ONGOING("Ongoing", "anime/ongoing?order_by=updated"),
    FINISHED("Finished", "anime/finished?order_by=updated"),
    MOVIE("Movie", "anime/movie?order_by=updated"),
    SPOTLIGHT("Spotlight")
}