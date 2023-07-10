package com.wahidabd.animein.data.anime

import androidx.paging.PagingData
import com.wahidabd.animein.data.anime.model.AnimeResponse
import com.wahidabd.animein.data.anime.model.CarouselResponse
import com.wahidabd.animein.domain.anime.model.Carousel
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


interface AnimeRepository {
    suspend fun anime(): Flow<PagingData<AnimeResponse>>
    suspend fun carousel(): Flow<Resource<List<CarouselResponse>>>
}