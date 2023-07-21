package com.wahidabd.animein.data

import com.wahidabd.animein.data.anime.model.CarouselResponse
import com.wahidabd.animein.domain.anime.model.AnimeDetail
import com.wahidabd.animein.domain.anime.model.Episode
import com.wahidabd.animein.domain.anime.model.Genre
import com.wahidabd.animein.utils.replaceSynopsis
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.extensions.debug
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.util.concurrent.TimeoutException


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
        )
        result.add(carousel)
    }
    return result
}

fun parseAnimeDetail(document: Document): Resource<AnimeDetail> {

    val widget = document.getElementsByClass("anime__details__widget")
    val left = widget.select(" div.row > div:eq(0) > ul > li")
    val right = widget.select("div.row > div:eq(1) > ul > li")

    return try {
        val poster = document.getElementsByClass("anime__details__pic").attr("data-setbg")
        val title = document.getElementsByClass("anime__details__title").select("h3")
            .text() //bottomElement.select("div.anime__details__title > h3").text()
        val type = left.eq(0).select("a").text()
        val totalEpisode = left.eq(1).select("a").text()
        val release = left.eq(3).select("a").text()
        val resolution = left.eq(6).select("a").text()
        val synopsis = document.getElementById("synopsisField")?.text()?.replaceSynopsis()
        val rating = right.eq(5).select("a").text().replace(" / 10.00", "")
        val genres = parseTextGenres(right.eq(0).select("a"))
        val alternative = document.getElementsByClass("anime__details__title").select("span").text()
        val episode = parseLastEpisode(
            document.getElementById("episodeLists")?.attr("data-content").toString()
        )


        val result = AnimeDetail(
            poster = poster,
            backdrop = poster,
            title = title,
            type = type,
            resolution = resolution,
            totalEpisode = totalEpisode,
            synopsis = synopsis,
            releaseEndDate = release,
            rating = rating,
            alternative = alternative,
            genres = genres,
            episode = episode
        )

        Resource.success(result)
    } catch (e: Exception) {
        when (e) {
            is TimeoutException -> Resource.fail(e.message.toString())
            else -> Resource.fail(e.message)
        }
    }
}

private fun parseLastEpisode(html: String): Episode {

    var slug = emptyString()
    var name = emptyString()

    val jsoup = Jsoup.parse(html)
    val el = jsoup.select("a")
    val size = el.size

    for (i in 0 until size) {
        if (el.eq(i).text().contains("(Terbaru)")) {
            slug = el.eq(i).attr("href")
            name = el.eq(i).text().replace("Ep", "Episode")
        }
    }

    debug { "$slug -> $name" }
    return Episode(slug, name)
}

private fun parseTextGenres(el: Elements): List<Genre> {
    val result = mutableListOf<Genre>()

    val size = el.size
    for (i in 0 until size) {
        val name = el.eq(i).text().replace(",", "")
        val slug = el.eq(i).attr("href")

        val genre = Genre(slug = slug, name = name)
        result.add(genre)
    }
    return result
}

private fun testEpisode(document: Document) {
    val parseToHtml = document.getElementById("episodeLists")?.attr("data-content")

    val jsoup = Jsoup.parse(parseToHtml.toString())
    val el = jsoup.select("a")
    val size = el.size

    for (i in 0 until size) {
        if (!el.eq(i).text().contains("(")) {
            debug { el.eq(i).text() }
        }
    }
}


