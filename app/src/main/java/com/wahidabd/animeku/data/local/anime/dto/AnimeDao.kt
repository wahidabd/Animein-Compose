package com.wahidabd.animeku.data.local.anime.dto

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wahidabd.library.data.CoroutineLocalDb
import com.wahidabd.library.data.LocalDb
import kotlinx.coroutines.flow.Flow


/**
 * Created by wahid on 11/25/2023.
 * Github github.com/wahidabd.
 */


@Dao
abstract class AnimeDao : CoroutineLocalDb<AnimeEntity>{

    @Query("SELECT * FROM anime")
    abstract override fun getList(): Flow<List<AnimeEntity>>

    @Query("SELECT * FROM anime WHERE slug = :id")
    abstract override fun get(id: Int?): Flow<AnimeEntity>

    @Query("SELECT * FROM anime WHERE slug = :slug")
    abstract fun get(slug: String?): Flow<AnimeEntity>?
}