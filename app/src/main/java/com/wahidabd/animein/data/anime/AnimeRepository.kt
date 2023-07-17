package com.wahidabd.animein.data.anime

import androidx.paging.PagingData
import com.wahidabd.animein.data.anime.model.AnimeResponse
import com.wahidabd.animein.data.anime.model.CarouselResponse
import com.wahidabd.animein.domain.anime.model.Anime
import com.wahidabd.animein.domain.anime.model.AnimeDetail
import com.wahidabd.animein.domain.anime.model.Carousel
import com.wahidabd.animein.domain.anime.model.Episode
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


interface AnimeRepository {
    suspend fun anime(q: String): Flow<PagingData<Anime>>
    suspend fun detail(slug: String): Flow<Resource<AnimeDetail>>
    suspend fun episode(slug: String): Flow<PagingData<Episode>>
    suspend fun carousel(): Flow<Resource<List<CarouselResponse>>>
}