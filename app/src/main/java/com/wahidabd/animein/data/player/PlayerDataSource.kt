package com.wahidabd.animein.data.player

import com.wahidabd.animein.data.getVideoUrl
import com.wahidabd.animein.data.hxFile
import com.wahidabd.animein.data.parsePlayer
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
//            val document = Jsoup.connect("https://wibufile.com/en/qh5H15qhxJDfGBC/watch").get()

            val desu = "https://desudrive.com/link/?id=eVYzczJaUk9LU0lNMEVHRVA4TzhVZzRtaDBoWWlzRlJMNitHalVVYQ=="
            emit(Resource.success(hxFile(desu)))


        }.flowOn(Dispatchers.IO)
    }


}

