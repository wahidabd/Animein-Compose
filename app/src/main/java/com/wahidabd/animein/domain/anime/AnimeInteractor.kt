package com.wahidabd.animein.domain.anime

import androidx.paging.PagingData
import com.wahidabd.animein.data.anime.AnimeRepository
import com.wahidabd.animein.data.anime.model.AnimeResponse
import com.wahidabd.animein.data.anime.model.CarouselResponse
import com.wahidabd.animein.domain.anime.model.Anime
import com.wahidabd.animein.domain.anime.model.Carousel
import com.wahidabd.animein.domain.anime.model.toDomain
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


class AnimeInteractor @Inject constructor(private val repository: AnimeRepository) : AnimeUseCase {

    override suspend fun anime(q: String): Flow<PagingData<Anime>> {
        return repository.anime(q)
    }

    override fun carousel(): Flow<Resource<List<Carousel>>> =
        object : InternetBoundResource<List<Carousel>, List<CarouselResponse>>() {
            override suspend fun createCall(): Flow<Resource<List<CarouselResponse>>> {
                return repository.carousel()
            }

            override suspend fun saveCallRequest(data: List<CarouselResponse>): List<Carousel> {
                return data.map {
                    it.toDomain()
                }
            }
        }.asFlow()

}