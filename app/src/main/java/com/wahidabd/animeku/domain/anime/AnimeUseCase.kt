package com.wahidabd.animeku.domain.anime

import androidx.paging.PagingData
import com.wahidabd.animeku.domain.anime.model.Anime
import com.wahidabd.animeku.domain.anime.model.AnimeDetail
import com.wahidabd.animeku.domain.anime.model.Episode
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
    suspend fun animeList(endpoint: String): Flow<PagingData<Anime>>
    suspend fun detail(slug: String): Flow<Resource<AnimeDetail>>
    suspend fun episode(slug: String): Flow<Resource<List<Episode>>>
}