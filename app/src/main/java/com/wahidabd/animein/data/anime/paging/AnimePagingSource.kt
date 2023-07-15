package com.wahidabd.animein.data.anime.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import com.wahidabd.animein.data.anime.model.AnimeResponse
import com.wahidabd.animein.utils.Constant
import com.wahidabd.library.utils.common.emptyString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


class AnimePagingSource(private val endpoint: String? = emptyString()) :
    PagingSource<Int, AnimeResponse>() {

    override fun getRefreshKey(state: PagingState<Int, AnimeResponse>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeResponse> {
        return try {
            val position = params.key ?: 1

            val url = if (position == 1) "${Constant.BASE_URL}$endpoint"
            else "${Constant.BASE_URL}$endpoint/page/$position/"

            val document = withContext(Dispatchers.IO) { Jsoup.connect(url).get() }
            val items = parseItem(document)

            LoadResult.Page(
                data = items,
                prevKey = if (position == 1) null else position.minus(1),
                nextKey = if (items.isEmpty()) null else position.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }


    private fun parseItem(document: Document): List<AnimeResponse> {
        val items = mutableListOf<AnimeResponse>()

        val events = document.select("div.listupd > div.excstf > article")
        val eventSize = events.size

        for (i in 0 until eventSize) {
            val slug = events.eq(i).select("div.bsx > a").attr("href")
            val title = events.eq(i).select("div.bsx > a").attr("title")
            val poster = events.eq(i).select("div.bsx > a > div.limit > img").attr("src")
            val rating =
                events.eq(i).select("div.bsx > a > div.tt > div.rt > div.rating > div.numscore")
                    .text()
            val episode = events.eq(i).select("div.bsx > a > div.tt > span.epsx").text()

            val anime = AnimeResponse(
                slug = slug,
                title = title,
                poster = poster,
                rating = rating,
                episode = episode,
            )

            items.add(anime)
        }

        return items
    }

}
