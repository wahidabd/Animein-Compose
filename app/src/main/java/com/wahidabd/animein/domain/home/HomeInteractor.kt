package com.wahidabd.animein.domain.home

import com.wahidabd.animein.data.home.HomeRepository
import com.wahidabd.animein.domain.anime.model.Anime
import com.wahidabd.animein.domain.anime.model.Carousel
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by Wahid on 7/15/2023.
 * Github github.com/wahidabd.
 */


class HomeInteractor @Inject constructor(
    private val repository: HomeRepository
) : HomeUseCase {

    override suspend fun movie(): Flow<Resource<List<Anime>>> =
        repository.movie()

    override suspend fun ongoing(): Flow<Resource<List<Anime>>> =
        repository.ongoing()

    override suspend fun finished(): Flow<Resource<List<Anime>>> =
        repository.finished()

    override suspend fun carousel(): Flow<Resource<List<Carousel>>> =
        repository.carousel()
}