package com.wahidabd.animeku.data.anime

import com.wahidabd.animeku.data.anime.dto.AnimeResponse
import com.wahidabd.animeku.data.parseAnime
import com.wahidabd.animeku.utils.constants.Endpoints
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup


/**
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */


class AnimeDataSource : AnimeRepository {

    override suspend fun spotlight(): Flow<Resource<List<AnimeResponse>>> =
        withContext(Dispatchers.IO) {
            flow {
                val doc = Jsoup.connect(Endpoints.SPOTLIGHT).get()
                val result = parseAnime(doc)

                if (result.isNotEmpty()) emit(Resource.Success(result))
                else emit(Resource.empty())
            }.flowOn(Dispatchers.IO)
        }

    override suspend fun populars(): Flow<Resource<List<AnimeResponse>>> = flow {
        val doc = Jsoup.connect(Endpoints.POPULAR).get()
        val result = parseAnime(doc)

        if (result.isNotEmpty()) emit(Resource.Success(result))
        else emit(Resource.empty())
    }.flowOn(Dispatchers.IO)

    override suspend fun movies(): Flow<Resource<List<AnimeResponse>>> = flow {
        val doc = Jsoup.connect(Endpoints.MOVIE).get()
        val result = parseAnime(doc)

        if (result.isNotEmpty()) emit(Resource.Success(result))
        else emit(Resource.empty())
    }.flowOn(Dispatchers.IO)

    override suspend fun ongoings(): Flow<Resource<List<AnimeResponse>>> = flow {
        val doc = Jsoup.connect(Endpoints.ONGOING).get()
        val result = parseAnime(doc)

        if (result.isNotEmpty()) emit(Resource.Success(result))
        else emit(Resource.empty())
    }.flowOn(Dispatchers.IO)

    override suspend fun finished(): Flow<Resource<List<AnimeResponse>>> = flow {
        val doc = Jsoup.connect(Endpoints.FINISH).get()
        val result = parseAnime(doc)

        if (result.isNotEmpty()) emit(Resource.Success(result))
        else emit(Resource.empty())
    }.flowOn(Dispatchers.IO)

}