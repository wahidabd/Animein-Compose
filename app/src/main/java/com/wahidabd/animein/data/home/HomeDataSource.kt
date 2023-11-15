package com.wahidabd.animein.data.home

import com.wahidabd.animein.data.blogger
import com.wahidabd.animein.domain.anime.model.Anime
import com.wahidabd.animein.domain.anime.model.Carousel
import com.wahidabd.animein.utils.Constant.BASE_URL
import com.wahidabd.animein.utils.replaceFullSlug
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

    override suspend fun movie(): Flow<Resource<List<Anime>>> = flow {
        try {
            val jsoup = Jsoup.connect(BASE_URL).get()
            val events = jsoup.select("div.trending__product")[2]
                .select("div.row")[1].select("div.product__item")
            emit(parseItem(events))
            blogger("https://www.blogger.com/video.g?token=AD6v5dxyZeqYUtZ2YsGxKZRZ0RvGnzCKZpgAdXkwg5TBaQdwTuIuHTcFYAgnncoQN-kKznYVHH4cHZa9agBu7LoKde6zBZUT0jz02DtgSLsidLiwCopZ4QS4mmlSPIIa4ML59dUFz94")
        }catch (e: Exception){
            when (e) {
                is TimeoutException -> emit(Resource.fail(e.message.toString()))
                else -> emit(Resource.fail(e.message))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun ongoing(): Flow<Resource<List<Anime>>> = flow {
        try {
            val jsoup = Jsoup.connect(BASE_URL).get()
            val events = jsoup.select("div.trending__product").eq(0)
                .select("div.row").eq(1).select("div.product__item")
            emit(parseItem(events))
        }catch (e: Exception){
            when (e) {
                is TimeoutException -> emit(Resource.fail(e.message.toString()))
                else -> emit(Resource.fail(e.message))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun finished(): Flow<Resource<List<Anime>>> = flow {
        try {
            val jsoup = Jsoup.connect(BASE_URL).get()
            val events = jsoup.select("div.trending__product")[1]
                .select("div.row")[1].select("div.product__item")
            emit(parseItem(events))
        }catch (e: Exception){
            when (e) {
                is TimeoutException -> emit(Resource.fail(e.message.toString()))
                else -> emit(Resource.fail(e.message))
            }
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun carousel(): Flow<Resource<List<Carousel>>> = flow {
        try {
            val jsoup = Jsoup.connect(BASE_URL).get()
            val events = jsoup.select("div.hero__slider > div.hero__items")
            emit(parseCarousel(events))
        }catch (e: Exception){
            when (e) {
                is TimeoutException -> emit(Resource.fail(e.message.toString()))
                else -> emit(Resource.fail(e.message))
            }
        }

    }.flowOn(Dispatchers.IO)


    private fun parseCarousel(el: Elements): Resource<List<Carousel>> {
        val result = mutableListOf<Carousel>()

        return try {
            val size = el.size

            for (i in 0 until size) {
                val poster = el.eq(i).attr("data-setbg")
                val title = el.eq(i).select("div.row > div.col-lg-6 > div.hero__text > h2").text()
                val slug = el.eq(i).select("div.row > div.col-lg-6 > div.hero__text > a").last()
                    ?.attr("href")

                val carousel = Carousel(slug = slug, title = title, image = poster)
                result.add(carousel)
            }
            if (result.isEmpty()) Resource.empty()
            else Resource.success(result)
        } catch (e: Exception) {
            when (e) {
                is TimeoutException -> Resource.fail(e.message.toString())
                else -> Resource.fail(e.message)
            }
        }
    }

    private fun parseItem(el: Elements): Resource<List<Anime>> {
        val result = mutableListOf<Anime>()

        return try {
            val size = el.size
            for (i in 0 until size) {
                val slug = el.eq(i).select("a").attr("href")
                    .replaceFullSlug()
                val poster = el.eq(i).select("a > div.product__item__pic")
                    .attr("data-setbg")
                val title = el.eq(i).select("div.product__item__text > h5 > a").text()
                val type = el.eq(i).select("div.product__item__text > ul > a")
                    .first()?.select("li")?.text()
                val resolution = el.eq(i).select("div.product__item__text > ul > a")
                    .last()?.select("li")?.text()
                val episode = el.eq(i).select("a > div.product__item__pic > div.ep > span").text()

                val anime = Anime(
                    slug = slug,
                    title = title,
                    poster = poster,
                    type = type,
                    episode = episode,
                    resolution = resolution
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