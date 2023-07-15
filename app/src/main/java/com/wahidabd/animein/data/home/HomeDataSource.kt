package com.wahidabd.animein.data.home

import com.wahidabd.animein.domain.anime.model.Anime
import com.wahidabd.animein.utils.Constant.BASE_URL
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.util.concurrent.TimeoutException


/**
 * Created by Wahid on 7/15/2023.
 * Github github.com/wahidabd.
 */


class HomeDataSource : HomeRepository {

    override suspend fun popular(): Flow<Resource<List<Anime>>> = flow {
        val jsoup = Jsoup.connect(BASE_URL).get()
        val events = jsoup.select("div.listupd > article")
        emit(parseItem(events))
    }.flowOn(Dispatchers.IO)

    override suspend fun newUpdate(): Flow<Resource<List<Anime>>> = flow {
        val jsoup = Jsoup.connect(BASE_URL).get()
        val events = jsoup.select("div.listupd > div.excstf > article")
        emit(parseItem(events))
    }.flowOn(Dispatchers.IO)

    override suspend fun newAdded(): Flow<Resource<List<Anime>>> = flow {
        val jsoup = Jsoup.connect("${BASE_URL}advanced-search/?status=&order=added").get()
        val events = jsoup.select("div.listupd > article")
        emit(parseItem(events))
    }.flowOn(Dispatchers.IO)

    private fun parseItem(el: Elements): Resource<List<Anime>> {
        val result = mutableListOf<Anime>()

        return try {
            val size = el.size
            for (i in 0 until size) {
                val slug = el.eq(i).select("div.bsx > a").attr("href")
                val title = el.eq(i).select("div.bsx > a").attr("title")
                val poster = el.eq(i).select("div.bsx > a > div.limit > img").attr("src")
                    .replace("-200x300", "")
                val type = el.eq(i).select("div.bsx > a > div.limit > div.typez").text()
                val rating =
                    el.eq(i).select("div.bsx > a > div.rt > div.rating > div.numscore").text()
                val episode = el.eq(i).select("div.bsx > a > div.tt > span.epsx").text()

                val anime = Anime(
                    slug = slug,
                    title = title,
                    poster = poster,
                    type = type,
                    rating = rating,
                    episode = episode
                )
                result.add(anime)
            }
            if (result.isEmpty()) Resource.empty()
            else Resource.success(result)
        } catch (e: Exception) {
            when (e) {
                is TimeoutException -> Resource.fail(e.message.toString())
                else -> Resource.fail("Unknown Error Exception")
            }
        }

    }
}