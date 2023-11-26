package com.wahidabd.animeku.data.local.anime

import com.wahidabd.animeku.data.local.anime.dto.AnimeEntity
import com.wahidabd.library.data.BaseRepository
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 11/25/2023.
 * Github github.com/wahidabd.
 */


interface AnimeLocalRepository : BaseRepository {

    suspend fun save(data: AnimeEntity)
    fun getList() : Flow<List<AnimeEntity>>
    fun get(slug: String): Flow<AnimeEntity>?
    suspend fun remove(data: AnimeEntity)

}