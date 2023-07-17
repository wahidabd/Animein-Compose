package com.wahidabd.animein.data.anime

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wahidabd.animein.data.anime.model.AnimeResponse
import com.wahidabd.animein.data.anime.model.CarouselResponse
import com.wahidabd.animein.data.anime.paging.AnimePagingSource
import com.wahidabd.animein.data.parseAnimeDetail
import com.wahidabd.animein.data.parseCarousel
import com.wahidabd.animein.domain.anime.model.Anime
import com.wahidabd.animein.domain.anime.model.AnimeDetail
import com.wahidabd.animein.utils.Constant.BASE_URL
import com.wahidabd.animein.utils.Constant.USER_AGENT
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.extensions.debug
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Singleton


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */

class AnimeDataSource : AnimeRepository {

    override suspend fun anime(q: String): Flow<PagingData<Anime>> =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ), pagingSourceFactory = { AnimePagingSource(q) }
        ).flow

    override suspend fun detail(slug: String): Flow<Resource<AnimeDetail>> = flow {
        val jsoup = Jsoup.connect("${BASE_URL}anime/$slug").get()
        emit(parseAnimeDetail(jsoup))
    }.flowOn(Dispatchers.IO)


    override suspend fun carousel(): Flow<Resource<List<CarouselResponse>>> =
        withContext(Dispatchers.IO) {
            flow {
                val document = Jsoup.connect("https://zoronime.com/home/").get()
                val result = parseCarousel(document)
                if (result.isEmpty()) emit(Resource.empty())
                else emit(Resource.success(result))

            }.flowOn(Dispatchers.IO)
        }

}
