package com.wahidabd.animein.domain.player

import com.wahidabd.animein.data.player.PlayerRepository
import com.wahidabd.animein.domain.player.domain.PlayerSource
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by Wahid on 7/10/2023.
 * Github github.com/wahidabd.
 */


class PlayerInteractor @Inject constructor(
    private val repository: PlayerRepository
) : PlayerUseCase {
    override fun player(url: String): Flow<Resource<List<PlayerSource>>> {
        return object : InternetBoundResource<List<PlayerSource>, List<PlayerSource>>() {
            override suspend fun createCall(): Flow<Resource<List<PlayerSource>>> {
                return repository.player(url)
            }

            override suspend fun saveCallRequest(data: List<PlayerSource>): List<PlayerSource> {
                return data
            }

        }.asFlow()
    }

    override fun videoUrl(url: String): Flow<Resource<String>> {
        return object : InternetBoundResource<String, String>() {
            override suspend fun createCall(): Flow<Resource<String>> {
                return repository.videoUrl(url)
            }

            override suspend fun saveCallRequest(data: String): String {
                return data
            }

        }.asFlow()
    }
}