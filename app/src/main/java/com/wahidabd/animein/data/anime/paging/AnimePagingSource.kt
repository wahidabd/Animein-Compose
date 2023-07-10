package com.wahidabd.animein.data.anime.paging

import android.telecom.CallEndpoint
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import coil.network.HttpException
import com.wahidabd.animein.data.anime.model.AnimeResponse
import com.wahidabd.library.utils.extensions.debug
import io.reactivex.rxjava3.core.Single
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


class AnimePagingSource(private val endpoint: String) : PagingSource<Int, AnimeResponse>() {

    override fun getRefreshKey(state: PagingState<Int, AnimeResponse>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeResponse> {
        return try {
            val position = params.key ?: 1

            val url = if (position == 1) "https://zoronime.com/$endpoint/"
            else "https://zoronime.com/$endpoint/page/$position/"

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
