package com.wahidabd.animein.data.player

import com.wahidabd.animein.data.kuramanime
import com.wahidabd.animein.data.parserServer
import com.wahidabd.animein.domain.player.domain.PlayerSource
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.extensions.debug
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.jsoup.Jsoup


/**
 * Created by Wahid on 7/10/2023.
 * Github github.com/wahidabd.
 */


class PlayerDataSource : PlayerRepository {

    override suspend fun player(url: String): Flow<Resource<List<PlayerSource>>> =
        flow {
            val jsoup = Jsoup.connect(url).get()

            val result = parserServer(url, jsoup)
            emit(result)

        }.flowOn(Dispatchers.IO)

    override suspend fun videoUrl(url: String): Flow<Resource<String>> {
        return flow {
//            emit(mediafire("https://www.mediafire.com/file/fji0gaa1jssobr9/Alqanime_IsekaiVendingMachine_02_360p.mp4/file"))

            debug { "VIDEO URL" }
            emit(kuramanime("https://kuramanime.xyz/anime/50/one-piece/episode/989?activate_stream=1&stream_server=kuramadrive"))
        }.flowOn(Dispatchers.IO)
    }


}

