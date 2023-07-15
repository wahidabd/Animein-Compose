package com.wahidabd.animein.domain.home

import com.wahidabd.animein.domain.anime.model.Anime
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 7/15/2023.
 * Github github.com/wahidabd.
 */


interface HomeUseCase {
    suspend fun popular(): Flow<Resource<List<Anime>>>
    suspend fun newUpdate(): Flow<Resource<List<Anime>>>
    suspend fun newAdded(): Flow<Resource<List<Anime>>>
}