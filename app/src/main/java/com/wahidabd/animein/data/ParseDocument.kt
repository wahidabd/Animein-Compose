package com.wahidabd.animein.data

import com.wahidabd.animein.data.anime.model.CarouselResponse
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.extensions.debug
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

    for (i in 0 until size) {
        val poster =
            events.eq(i).select("div.deslide-item > div.deslide-cover > div > img").attr("data-src")
        val title =
            events.eq(i).select("div.deslide-item-content > div.desi-head-title.dynamic-name")
                .text()
        val slug =
            events.eq(i).select("div.deslide-item-content > div.desi-buttons > a")[1].attr("href")
        val type = events.eq(i)
            .select("div.deslide-item > div.deslide-item-content > div.sc-detail > div.scd-item")[0].select(
            "a"
        ).text()
        val rating = events.eq(i)
            .select("div.deslide-item > div.deslide-item-content > div.sc-detail > div.scd-item.mr-1 > span")
            .text()

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

fun parsePlayer(document: Document): List<String> {
    val events = document.select("div.ps__-list")[1].select("div.item")
    val size = events.size
    val servers = mutableListOf<String>()

    for (i in 0 until size) {
        val server = events.eq(i).select("a").attr("href")
        if (server.contains("uservideo")) {
            servers.add(server)
        }
        debug { "SERVER -> $server" }
    }

    return servers

}


// otakudesu
//fun getVideoUrl(document: Document): String {
//    val tag = document.getElementsByTag("script")
//
//    for (t in tag){
//        if (t.data().contains("sources:")){
//            val subString = t.data().substringAfter("[{'file':'").substringBefore("}]")
//            debug { subString }
//            debug { t.data() }
////            val pattern: Pattern = Pattern.compile("sources: ([^;]*);")
////            val matcher: Matcher = pattern.matcher(t.data())
////            if (matcher.find()){
////                debug { matcher.group() }
////            }
////            else{
////                debug { "Not Found" }
////            }
//
//        }
//    }
//
//    return "OK"
//}


fun anoboy(url: String): Resource<String> {
    val jsoup = Jsoup.connect(url).get()




    return Resource.success("OK")
}


