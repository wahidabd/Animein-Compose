package com.wahidabd.animein.data.anime

import com.wahidabd.animein.data.anime.model.CarouselResponse
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


/**
 * Created by Wahid on 7/10/2023.
 * Github github.com/wahidabd.
 */


fun parseCarousel(document: Document): List<CarouselResponse> {
    val result = mutableListOf<CarouselResponse>()

    val events = document.select("div.swiper-slide")
    val size = events.size

    for (i in 0 until size){
        val poster = events.eq(i).select("div.deslide-item > div.deslide-cover > div > img").attr("data-src")
        val title = events.eq(i).select("div.deslide-item-content > div.desi-head-title.dynamic-name").text()
        val slug = events.eq(i).select("div.deslide-item-content > div.desi-buttons > a")[1].attr("href")
        val type = events.eq(i).select("div.deslide-item > div.deslide-item-content > div.sc-detail > div.scd-item")[0].select("a").text()
        val rating =  events.eq(i).select("div.deslide-item > div.deslide-item-content > div.sc-detail > div.scd-item.mr-1 > span").text()

        val carousel = CarouselResponse(
            slug = slug,
            title = title,
            image = poster,
            type = type,
            rating = rating
        )
        result.add(carousel)
    }


    return result
}