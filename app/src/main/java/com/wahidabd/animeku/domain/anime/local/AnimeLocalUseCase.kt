package com.wahidabd.animeku.domain.anime.local

import com.wahidabd.animeku.domain.anime.model.Anime
import com.wahidabd.animeku.domain.anime.model.AnimeDetail
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 11/25/2023.
 * Github github.com/wahidabd.
 */


interface AnimeLocalUseCase {
    suspend fun save(anime: AnimeDetail)
    fun get(slug: String): Flow<AnimeDetail>?
    fun getAll(): Flow<List<Anime>>
    suspend fun delete(anime: AnimeDetail)
}