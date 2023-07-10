package com.wahidabd.animein.domain.anime

import androidx.paging.PagingData
import com.wahidabd.animein.data.anime.AnimeRepository
import com.wahidabd.animein.data.anime.model.AnimeResponse
import com.wahidabd.animein.data.anime.model.CarouselResponse
import com.wahidabd.animein.domain.anime.model.Carousel
import com.wahidabd.animein.domain.anime.model.toDomain
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


class AnimeInteractor(private val repository: AnimeRepository) : AnimeUseCase {

    override suspend fun anime(): Flow<PagingData<AnimeResponse>> {
        return repository.anime()
    }

    override fun carousel(): Flow<Resource<List<Carousel>>> =
        object : InternetBoundResource<List<Carousel>, List<CarouselResponse>>(){
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