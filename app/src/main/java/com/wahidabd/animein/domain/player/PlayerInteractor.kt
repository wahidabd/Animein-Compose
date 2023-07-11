package com.wahidabd.animein.domain.player

import com.wahidabd.animein.data.player.PlayerRepository
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.coroutine.boundResource.InternetBoundResource
import kotlinx.coroutines.flow.Flow


/**
 * Created by Wahid on 7/10/2023.
 * Github github.com/wahidabd.
 */


class PlayerInteractor(private val repository: PlayerRepository) : PlayerUseCase{
    override fun player(): Flow<Resource<List<String>>> {
        return object : InternetBoundResource<List<String>, List<String>>(){
            override suspend fun createCall(): Flow<Resource<List<String>>> {
                return repository.player()
            }

            override suspend fun saveCallRequest(data: List<String>): List<String> {
                return data
            }
        }.asFlow()
    }

    override fun videoUrl(url: String): Flow<Resource<String>> {
        return object : InternetBoundResource<String, String>(){
            override suspend fun createCall(): Flow<Resource<String>> {
                return repository.videoUrl(url)
            }

            override suspend fun saveCallRequest(data: String): String {
                return data
            }

        }.asFlow()
    }
}