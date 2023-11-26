package com.wahidabd.animeku.data.remote.anime

import androidx.paging.PagingData
import com.wahidabd.animeku.data.remote.anime.dto.AnimeDetailResponse
import com.wahidabd.animeku.data.remote.anime.dto.AnimeResponse
import com.wahidabd.animeku.data.remote.anime.dto.EpisodeResponse
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
    suspend fun animeList(endpoint: String): Flow<PagingData<AnimeResponse>>
    suspend fun detail(slug: String): Flow<Resource<AnimeDetailResponse>>
    suspend fun episode(slug: String): Flow<Resource<List<EpisodeResponse>>>
    suspend fun search(q: String): Flow<PagingData<AnimeResponse>>
}