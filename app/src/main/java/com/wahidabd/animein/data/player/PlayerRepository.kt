package com.wahidabd.animein.data.player

import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 7/10/2023.
 * Github github.com/wahidabd.
 */


interface PlayerRepository {

    suspend fun player(): Flow<Resource<List<String>>>
    suspend fun videoUrl(url: String): Flow<Resource<String>>

}