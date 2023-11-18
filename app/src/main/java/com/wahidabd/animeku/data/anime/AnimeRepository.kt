package com.wahidabd.animeku.data.anime

import com.wahidabd.animeku.data.anime.dto.AnimeResponse
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */


interface AnimeRepository {
    suspend fun spotlight(): Flow<Resource<List<AnimeResponse>>>
    suspend fun populars(): Flow<Resource<List<AnimeResponse>>>
    suspend fun movies(): Flow<Resource<List<AnimeResponse>>>
    suspend fun ongoings(): Flow<Resource<List<AnimeResponse>>>
    suspend fun finished(): Flow<Resource<List<AnimeResponse>>>
}