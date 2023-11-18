package com.wahidabd.animeku.domain.anime

import com.wahidabd.animeku.data.anime.AnimeRepository
import com.wahidabd.animeku.data.anime.dto.AnimeResponse
import com.wahidabd.animeku.domain.anime.model.Anime
import com.wahidabd.animeku.domain.anime.model.toDomain
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import kotlinx.coroutines.flow.Flow
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
}