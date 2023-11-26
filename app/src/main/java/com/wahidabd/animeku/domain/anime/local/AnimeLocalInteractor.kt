package com.wahidabd.animeku.domain.anime.local

import com.wahidabd.animeku.data.local.anime.AnimeLocalRepository
import com.wahidabd.animeku.domain.anime.model.Anime
import com.wahidabd.animeku.domain.anime.model.AnimeDetail
import com.wahidabd.animeku.domain.anime.model.mapper.toAnime
import com.wahidabd.animeku.domain.anime.model.mapper.toDomain
import com.wahidabd.animeku.domain.anime.model.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * Created by wahid on 11/25/2023.
 * Github github.com/wahidabd.
 */


class AnimeLocalInteractor @Inject constructor(
    private val data: AnimeLocalRepository
) : AnimeLocalUseCase {
    override suspend fun save(anime: AnimeDetail) {
        return data.save(anime.toEntity())
    }

    override fun get(slug: String): Flow<AnimeDetail>? {
        return data.get(slug)?.map { anime ->
            anime.toDomain()
        }
    }

    override fun getAll(): Flow<List<Anime>> {
        return data.getList().map { animeList ->
            animeList.map { anime ->
                anime.toAnime()
            }
        }
    }

    override suspend fun delete(anime: AnimeDetail) {
        return data.remove(anime.toEntity())
    }
}