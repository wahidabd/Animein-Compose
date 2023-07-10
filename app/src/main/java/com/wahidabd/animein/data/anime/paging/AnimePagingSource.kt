package com.wahidabd.animein.data.anime.paging

import android.telecom.CallEndpoint
import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import com.wahidabd.animein.data.anime.model.AnimeResponse
import com.wahidabd.library.utils.extensions.debug
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


class AnimePagingSource : PagingSource<Int, AnimeResponse>() {

    override fun getRefreshKey(state: PagingState<Int, AnimeResponse>): Int? =
        state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeResponse> {
        return try {
            val position = params.key ?: 1

            val items = ArrayList<AnimeResponse>()
            val url = if (position == 1) "https://zoronime.com/anime/"
                else "https://zoronime.com/anime/page/$position/"

            val document = withContext (Dispatchers.IO) {Jsoup.connect(url).get()}
            val events = document.select("div.film_list-wrap > div.flw-item")
            val eventSize = events.size

            for (i in 0 until eventSize) {
                val slug = events.eq(i).select("div.film-detail > h3 > a").attr("href")
                val title = events.eq(i).select("div.film-detail > h3 > a").attr("title")
                val poster = events.eq(i).select("div.film-poster > img").attr("data-src")
                val rating = events.eq(i).select("div.film-poster > div.tick.ltr > div").text()
                val episode = events.eq(i).select("div.film-poster > div.tick.rtl > div").text()
                val subtitle = events.eq(i).select("div.film-detail > div.fd-infor > span")[0].text()
                val season = events.eq(i).select("div.film-detail > div.fd-infor > span")[2].text()

                val anime = AnimeResponse(
                    slug = slug,
                    title = title,
                    poster = poster,
                    rating = rating,
                    episode = episode,
                    season = season,
                    subtitle = subtitle
                )

                items.add(anime)
            }

            debug { "Item position -> $position" }
            LoadResult.Page(
                data = items,
                prevKey = if (position == 1) null else position.minus(1),
                nextKey = if (items.isNotEmpty()) null else position.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }


    private fun parseItem(document: Document): List<AnimeResponse> {
        val items = mutableListOf<AnimeResponse>()

        val events = document.select("div.film_list-wrap > div.flw-item")
        val eventSize = events.size

        for (i in 0 until eventSize) {
            val slug = events.eq(i).select("div.film-detail > h3 > a").attr("href")
            val title = events.eq(i).select("div.film-detail > h3 > a").attr("title")
            val poster = events.eq(i).select("div.film-poster > img").attr("data-src")
            val rating = events.eq(i).select("div.film-poster > div.tick.ltr > div").text()
            val episode = events.eq(i).select("div.film-poster > div.tick.rtl > div").text()
            val subtitle = events.eq(i).select("div.film-detail > div.fd-infor > span")[0].text()
            val season = events.eq(i).select("div.film-detail > div.fd-infor > span")[2].text()

            val anime = AnimeResponse(
                slug = slug,
                title = title,
                poster = poster,
                rating = rating,
                episode = episode,
                season = season,
                subtitle = subtitle
            )

            items.add(anime)
        }

        return items
    }
}