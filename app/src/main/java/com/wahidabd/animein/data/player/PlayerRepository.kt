package com.wahidabd.animein.data.player

import com.wahidabd.animein.domain.player.domain.PlayerSource
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 7/10/2023.
 * Github github.com/wahidabd.
 */


interface PlayerRepository {

    suspend fun player(url: String): Flow<Resource<List<PlayerSource>>>
    suspend fun videoUrl(url: String): Flow<Resource<String>>

}