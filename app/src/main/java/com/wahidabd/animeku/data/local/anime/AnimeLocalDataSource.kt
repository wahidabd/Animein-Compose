package com.wahidabd.animeku.data.local.anime

import com.wahidabd.animeku.data.local.AppDatabase
import com.wahidabd.animeku.data.local.anime.dto.AnimeEntity
import com.wahidabd.library.data.WebApi
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 11/25/2023.
 * Github github.com/wahidabd.
 */


class AnimeLocalDataSource(
    db: AppDatabase
) : AnimeLocalRepository{

    override val dbService = db.animeDao()
    override val webService: WebApi? = null

    override suspend fun save(data: AnimeEntity) {
        dbService.save(data)
    }

    override fun getList(): Flow<List<AnimeEntity>> {
        return dbService.getList()
    }

    override fun get(slug: String): Flow<AnimeEntity>? {
        return dbService.get(slug)
    }

    override suspend fun remove(data: AnimeEntity) {
        dbService.remove(data)
    }
}