package com.wahidabd.animeku.data.remote

import com.wahidabd.animeku.data.remote.anime.dto.AnimeDetailResponse
import com.wahidabd.animeku.data.remote.anime.dto.AnimeResponse
import com.wahidabd.animeku.data.remote.anime.dto.EpisodeResponse
import com.wahidabd.animeku.data.remote.anime.dto.GenreResponse
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

fun parseAnimeSearch(doc: Document): List<AnimeResponse>{
    val result = mutableListOf<AnimeResponse>()
    val event = doc.select("main > article")
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

fun parseAnimeDetail(doc: Document): AnimeDetailResponse {

    // event listener
    val event = doc.select("div.post-body")
    val eventHeader = event.select("div.infoanime.widget_senction > div.infox")
    val eventGenre = doc.select("div.genre-info > a")

    // list
    val genres = mutableListOf<GenreResponse>()

    // anime detail
    val poster = event.select("div.infoanime.widget_senction > div.thumb > img").attr("src")
    val title = event.select("div.infoanime.widget_senction > div.thumb > img").attr("title")
    val rating = doc.select("div.clearfix.archiveanime-rating > span").text()
        .replace("/", "").trim()
    val type = eventHeader.select("div.areatitle > div.alternati > span:eq(0)").text()
    val status = eventHeader.select("div.areatitle > div.alternati > span:eq(1)").text()
    val duration = eventHeader.select("div.areatitle > div.alternati > span:eq(2)").text()
        .replace(".", "")
        .trim()
    val season = eventHeader.select("div.areatitle > div.alternati > span:eq(3)").text()
    val synopsis = eventHeader.select("div.entry-content.entry-content-single > p")
        .joinToString("\n\n") { it.text() }
    val totalEpisode = doc.select("div.spe > span:eq(2)").text()
    val releaseDate = doc.select("div.spe > span:eq(3)").text()
    val studio = doc.select("div.spe > span:eq(4)").text()

    // genres
    for (i in 0 until eventGenre.size) {
        val genreSlug = eventGenre.eq(i).attr("href")
        val genreTitle = eventGenre.eq(i).text()
        genres.add(GenreResponse(slug = genreSlug, title = genreTitle))
    }

    return AnimeDetailResponse(
        poster = poster,
        title = title,
        rating = rating,
        type = type,
        status = status,
        duration = duration,
        season = season,
        synopsis = synopsis,
        totalEpisode = totalEpisode,
        releaseDate = releaseDate,
        studio = studio,
        genres = genres,
    )
}

fun parseEpisode(doc: Document): List<EpisodeResponse> {
    val result = mutableListOf<EpisodeResponse>()

    val eventEpisode = doc.select("div.lstepsiode.listeps > ul.scrolling > li")

    // episodes
    for (i in 0 until eventEpisode.size) {
        val episodeSlug = eventEpisode.eq(i).select("div.epsright > span > a").attr("href")
        val episodeTitle = eventEpisode.eq(i).select("div.epsleft > span.lchx > a").text()
        val episodeNumber = eventEpisode.eq(i).select("div.epsright > span > a").text()
        val episodeDate = eventEpisode.eq(i).select("div.epsleft > span.date").text()

        result.add(
            EpisodeResponse(
                slug = episodeSlug,
                title = episodeTitle,
                number = episodeNumber,
                date = episodeDate
            )
        )

        debug{"Episode Slug: $episodeSlug\n"}
    }

    return result
}