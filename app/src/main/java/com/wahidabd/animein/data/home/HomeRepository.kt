package com.wahidabd.animein.data.home

import com.wahidabd.animein.domain.anime.model.Anime
import com.wahidabd.animein.domain.anime.model.Carousel
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 7/15/2023.
 * Github github.com/wahidabd.
 */


interface HomeRepository {
    suspend fun movie(): Flow<Resource<List<Anime>>>
    suspend fun ongoing(): Flow<Resource<List<Anime>>>
    suspend fun finished(): Flow<Resource<List<Anime>>>
    suspend fun carousel(): Flow<Resource<List<Carousel>>>

}