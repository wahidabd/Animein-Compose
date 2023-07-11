package com.wahidabd.animein.data.anime

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wahidabd.animein.data.anime.model.AnimeResponse
import com.wahidabd.animein.data.anime.model.CarouselResponse
import com.wahidabd.animein.data.anime.paging.AnimePagingSource
import com.wahidabd.animein.data.parseCarousel
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


class AnimeDatSource : AnimeRepository {

    override suspend fun anime(): Flow<PagingData<AnimeResponse>> =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ), pagingSourceFactory = { AnimePagingSource("anime") }
        ).flow


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
