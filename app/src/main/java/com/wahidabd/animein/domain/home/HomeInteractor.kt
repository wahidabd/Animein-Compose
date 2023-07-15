package com.wahidabd.animein.domain.home

import com.wahidabd.animein.data.home.HomeRepository
import com.wahidabd.animein.domain.anime.model.Anime
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

    override suspend fun popular(): Flow<Resource<List<Anime>>> =
        repository.popular()

    override suspend fun newUpdate(): Flow<Resource<List<Anime>>> =
        repository.newUpdate()

    override suspend fun newAdded(): Flow<Resource<List<Anime>>> =
        repository.newAdded()
}