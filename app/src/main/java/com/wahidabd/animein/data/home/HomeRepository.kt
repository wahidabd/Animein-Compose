package com.wahidabd.animein.data.home

import com.wahidabd.animein.domain.anime.model.Anime
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 7/15/2023.
 * Github github.com/wahidabd.
 */


interface HomeRepository {
    suspend fun popular(): Flow<Resource<List<Anime>>>
}