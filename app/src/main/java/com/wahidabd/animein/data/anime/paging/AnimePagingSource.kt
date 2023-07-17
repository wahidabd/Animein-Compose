package com.wahidabd.animein.data.anime.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import com.wahidabd.animein.data.anime.model.AnimeResponse
import com.wahidabd.animein.domain.anime.model.Anime
import com.wahidabd.animein.utils.Constant
import com.wahidabd.animein.utils.replaceFullSlug
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.extensions.debug
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
    PagingSource<Int, Anime>() {

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        return try {
            val position = params.key ?: 1

            val url = if (position == 1) "${Constant.BASE_URL}$endpoint"
            else "${Constant.BASE_URL}$endpoint&page=$position"

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
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }


    private fun parseItem(document: Document): List<Anime> {
        val items = mutableListOf<Anime>()

        val events = document.select("div.product__page__content > div#animeList > div > div.product__item")
        val eventSize = events.size

        for (i in 0 until eventSize) {
            val slug = events.eq(i).select("a").attr("href").replaceFullSlug()
            val title = events.eq(i).select("div.product__item__text > h5 > a").text()
            val poster = events.eq(i).select("a > div.product__item__pic")
                .attr("data-setbg")
            val episode = events.eq(i)
                .select("a > div.product__item__pic > div.ep > span").text()
                .replace("Loading... ", "")
            val type = events.eq(i).select("div.product__item__text > ul > a")
                .first()?.select("li")?.text()
            val resolution = events.eq(i).select("div.product__item__text > ul > a")
                .last()?.select("li")?.text()

            val anime = Anime(
                slug = slug,
                title = title,
                poster = poster,
                episode = episode,
                type = type,
                resolution = resolution
            )

            items.add(anime)
        }

        return items
    }

}
