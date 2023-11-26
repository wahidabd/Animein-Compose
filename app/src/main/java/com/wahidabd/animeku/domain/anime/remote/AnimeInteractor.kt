package com.wahidabd.animeku.domain.anime.remote

import androidx.paging.PagingData
import androidx.paging.map
import com.wahidabd.animeku.data.remote.anime.AnimeRepository
import com.wahidabd.animeku.data.remote.anime.dto.AnimeDetailResponse
import com.wahidabd.animeku.data.remote.anime.dto.AnimeResponse
import com.wahidabd.animeku.data.remote.anime.dto.EpisodeResponse
import com.wahidabd.animeku.domain.anime.model.Anime
import com.wahidabd.animeku.domain.anime.model.AnimeDetail
import com.wahidabd.animeku.domain.anime.model.Episode
import com.wahidabd.animeku.domain.anime.model.mapper.toDomain
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


/**
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */


class AnimeInteractor @Inject constructor(private val repository: AnimeRepository) : AnimeUseCase {

    override suspend fun spotLight(): Flow<Resource<List<Anime>>> =
        object : InternetBoundResource<List<Anime>, List<AnimeResponse>>() {
            override suspend fun createCall(): Flow<Resource<List<AnimeResponse>>> {
                return repository.spotlight()
            }

            override suspend fun saveCallRequest(data: List<AnimeResponse>): List<Anime> {
                return data.map { anime ->
                    anime.toDomain()
                }
            }

        }.asFlow()

    override suspend fun populars(): Flow<Resource<List<Anime>>> =
        object : InternetBoundResource<List<Anime>, List<AnimeResponse>>() {
            override suspend fun createCall(): Flow<Resource<List<AnimeResponse>>> {
                return repository.populars()
            }

            override suspend fun saveCallRequest(data: List<AnimeResponse>): List<Anime> {
                return data.map { anime ->
                    anime.toDomain()
                }
            }

        }.asFlow()

    override suspend fun movies(): Flow<Resource<List<Anime>>> =
        object : InternetBoundResource<List<Anime>, List<AnimeResponse>>() {
            override suspend fun createCall(): Flow<Resource<List<AnimeResponse>>> {
                return repository.movies()
            }

            override suspend fun saveCallRequest(data: List<AnimeResponse>): List<Anime> {
                return data.map { anime ->
                    anime.toDomain()
                }
            }

        }.asFlow()

    override suspend fun ongoings(): Flow<Resource<List<Anime>>> =
        object : InternetBoundResource<List<Anime>, List<AnimeResponse>>() {
            override suspend fun createCall(): Flow<Resource<List<AnimeResponse>>> {
                return repository.ongoings()
            }

            override suspend fun saveCallRequest(data: List<AnimeResponse>): List<Anime> {
                return data.map { anime ->
                    anime.toDomain()
                }
            }

        }.asFlow()

    override suspend fun finished(): Flow<Resource<List<Anime>>> =
        object : InternetBoundResource<List<Anime>, List<AnimeResponse>>() {
            override suspend fun createCall(): Flow<Resource<List<AnimeResponse>>> {
                return repository.finished()
            }

            override suspend fun saveCallRequest(data: List<AnimeResponse>): List<Anime> {
                return data.map { anime ->
                    anime.toDomain()
                }
            }

        }.asFlow()

    override suspend fun animeList(endpoint: String): Flow<PagingData<Anime>> =
        repository.animeList(endpoint).map { pagingData ->
            pagingData.map { anime ->
                anime.toDomain()
            }
        }

    override suspend fun detail(slug: String): Flow<Resource<AnimeDetail>> =
        object : InternetBoundResource<AnimeDetail, AnimeDetailResponse>() {
            override suspend fun createCall(): Flow<Resource<AnimeDetailResponse>> {
                return repository.detail(slug)
            }

            override suspend fun saveCallRequest(data: AnimeDetailResponse): AnimeDetail {
                return data.toDomain()
            }
        }.asFlow()

    override suspend fun episode(slug: String): Flow<Resource<List<Episode>>> =
        object : InternetBoundResource<List<Episode>, List<EpisodeResponse>>() {
            override suspend fun createCall(): Flow<Resource<List<EpisodeResponse>>> {
                return repository.episode(slug)
            }

            override suspend fun saveCallRequest(data: List<EpisodeResponse>): List<Episode> {
                return data.map { episode ->
                    episode.toDomain()
                }
            }
        }.asFlow()

    override suspend fun search(q: String): Flow<PagingData<Anime>> {
        return repository.search(q).map { pagingData ->
            pagingData.map { anime ->
                anime.toDomain()
            }
        }
    }
}