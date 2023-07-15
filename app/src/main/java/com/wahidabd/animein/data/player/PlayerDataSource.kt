package com.wahidabd.animein.data.player

import com.wahidabd.animein.data.hxFile
import com.wahidabd.animein.data.mediafire
import com.wahidabd.animein.data.parsePlayer
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup
import javax.inject.Singleton


/**
 * Created by Wahid on 7/10/2023.
 * Github github.com/wahidabd.
 */


class PlayerDataSource : PlayerRepository {

    override suspend fun player(): Flow<Resource<List<String>>> =
        flow {
            val document =
                Jsoup.connect("https://zoronime.com/episode/jujutsu-kaisen-2nd-season-episode-001/")
                    .get()

            val result = parsePlayer(document)
            emit(Resource.success(result))

        }.flowOn(Dispatchers.IO)

    override suspend fun videoUrl(url: String): Flow<Resource<String>> {
        return flow {
            emit(mediafire("https://www.mediafire.com/file/fji0gaa1jssobr9/Alqanime_IsekaiVendingMachine_02_360p.mp4/file"))

        }.flowOn(Dispatchers.IO)
    }


}

