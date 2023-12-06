package com.wahidabd.animeku.data.remote.anime

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.wahidabd.animeku.data.remote.anime.dto.AnimeDetailResponse
import com.wahidabd.animeku.data.remote.anime.dto.AnimeResponse
import com.wahidabd.animeku.data.remote.anime.dto.EpisodeResponse
import com.wahidabd.animeku.data.remote.anime.paging.AnimePagingSource
import com.wahidabd.animeku.data.remote.parseAnime
import com.wahidabd.animeku.data.remote.parseAnimeDetail
import com.wahidabd.animeku.data.remote.parseEpisode
import com.wahidabd.animeku.utils.constants.Endpoints
import com.wahidabd.animeku.utils.enums.AnimeType
import com.wahidabd.animeku.utils.enums.PagingType
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup


/**
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */


class AnimeDataSource : AnimeRepository {

    override suspend fun spotlight(): Flow<Resource<List<AnimeResponse>>> = flow {
        try {
            val doc = Jsoup.connect(Endpoints.ANIME_LIST + Endpoints.SPOTLIGHT).get()
            val result = parseAnime(doc)

            if (result.isNotEmpty()) emit(Resource.Success(result))
            else emit(Resource.empty())
        }catch (e: Exception){
            emit(Resource.fail(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun populars(): Flow<Resource<List<AnimeResponse>>> = flow {
        try {
            val doc = Jsoup.connect(Endpoints.ANIME_LIST + Endpoints.POPULAR).get()
            val result = parseAnime(doc)

            if (result.isNotEmpty()) emit(Resource.Success(result))
            else emit(Resource.empty())
        } catch (e: Exception) {
            emit(Resource.fail(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun movies(): Flow<Resource<List<AnimeResponse>>> = flow {
        try {
            val doc = Jsoup.connect(Endpoints.ANIME_LIST + Endpoints.MOVIE).get()
            val result = parseAnime(doc)

            if (result.isNotEmpty()) emit(Resource.Success(result))
            else emit(Resource.empty())
        } catch (e: Exception) {
            emit(Resource.fail(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun ongoings(): Flow<Resource<List<AnimeResponse>>> = flow {
        try {
            val doc = Jsoup.connect(Endpoints.ANIME_LIST + Endpoints.ONGOING).get()
            val result = parseAnime(doc)

            if (result.isNotEmpty()) emit(Resource.Success(result))
            else emit(Resource.empty())
        } catch (e: Exception) {
            emit(Resource.fail(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun finished(): Flow<Resource<List<AnimeResponse>>> = flow {
        try {
            val doc = Jsoup.connect(Endpoints.ANIME_LIST + Endpoints.FINISH).get()
            val result = parseAnime(doc)

            if (result.isNotEmpty()) emit(Resource.Success(result))
            else emit(Resource.empty())
        } catch (e: Exception) {
            emit(Resource.fail(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun animeList(endpoint: String): Flow<PagingData<AnimeResponse>> =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ), pagingSourceFactory = { AnimePagingSource(endpoint) }
        ).flow

    override suspend fun detail(slug: String): Flow<Resource<AnimeDetailResponse>> = flow {
        try {
            val doc = Jsoup.connect(slug).get()
            val result = parseAnimeDetail(doc)

            emit(Resource.Success(result))
        } catch (e: Exception) {
            emit(Resource.fail(e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun episode(slug: String): Flow<Resource<List<EpisodeResponse>>> = flow {
        try {
            val doc = Jsoup.connect(slug).get()
            val result = parseEpisode(doc)

            if (result.isNotEmpty()) emit(Resource.Success(result))
            else emit(Resource.empty())
        } catch (e: Exception) {
            emit(Resource.fail(e.message.toString()))
        }

    }.flowOn(Dispatchers.IO)

    override suspend fun search(q: String): Flow<PagingData<AnimeResponse>> =
        try {
            Pager(
                config = PagingConfig(
                    pageSize = 10,
                    enablePlaceholders = false
                ), pagingSourceFactory = { AnimePagingSource(Endpoints.SEARCH + q, PagingType.SEARCH) }
            ).flow
        }catch (e: Exception){
            throw Exception(e.message.toString())
        }

    override suspend fun genres(endpoint: String): Flow<PagingData<AnimeResponse>> =
        try {
            Pager(
                config = PagingConfig(
                    pageSize = 10,
                    enablePlaceholders = false,
                ), pagingSourceFactory = {AnimePagingSource(endpoint, PagingType.GENRE)}
            ).flow
        }catch (e: Exception){
            throw Exception(e.message.toString())
        }

}