package com.wahidabd.animeku.data

import com.wahidabd.animeku.data.anime.dto.AnimeResponse
import com.wahidabd.library.utils.extensions.debug
import org.jsoup.nodes.Document


/**
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */

fun parseAnime(doc: Document): List<AnimeResponse> {
    val result = mutableListOf<AnimeResponse>()
    val event = doc.select("div.relat > article")
    val size = event.size

    for (i in 0 until size) {
        val content = event.eq(i).select("div > div > a")
        val slug = content.attr("href")
        val title = content.select("div.data > div.title > h2").text()
        val poster = content.select("div.content-thumb > img").attr("src")
        val type = content.select("div.content-thumb > div.type").text()
        val rating = content.select("div.content-thumb > div.score").text()
        val status = content.select("div.data > div.type").text()

        val item = AnimeResponse(
            slug = slug,
            title = title,
            poster = poster,
            type = type,
            rating = rating,
            status = status
        )
        result.add(item)
    }

    return result
}