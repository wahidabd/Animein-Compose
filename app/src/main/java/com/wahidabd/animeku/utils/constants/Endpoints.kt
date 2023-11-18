package com.wahidabd.animeku.utils.constants


/**
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */


object Endpoints {
    const val BASE_URL = "https://oploverz.red/"
    const val SPOTLIGHT = "${BASE_URL}anime-list/?order=popular&status=Currently+Airing"
    const val POPULAR = "${BASE_URL}anime-list/?order=popular"
    const val FINISH = "${BASE_URL}anime-list/?order=update&status=Finished+Airing"
    const val ONGOING = "${BASE_URL}anime-list/?order=updated&status=Currently+Airing"
    const val MOVIE = "${BASE_URL}anime-list/?order=updated&type=Movie"
}