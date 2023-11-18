package com.wahidabd.animeku.domain.anime

import com.wahidabd.animeku.domain.anime.model.Anime
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */


interface AnimeUseCase {
    suspend fun spotLight(): Flow<Resource<List<Anime>>>
    suspend fun populars(): Flow<Resource<List<Anime>>>
    suspend fun movies(): Flow<Resource<List<Anime>>>
    suspend fun ongoings(): Flow<Resource<List<Anime>>>
    suspend fun finished(): Flow<Resource<List<Anime>>>
}