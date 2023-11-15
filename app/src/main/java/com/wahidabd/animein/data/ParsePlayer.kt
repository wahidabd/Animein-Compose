package com.wahidabd.animein.data

import com.wahidabd.animein.domain.player.domain.PlayerSource
import com.wahidabd.animein.utils.Constant
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.extensions.debug
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.util.concurrent.TimeoutException


/**
 * Created by Wahid on 7/21/2023.
 * Github github.com/wahidabd.
 */

suspend fun parserServer(url: String, doc: Document): Resource<List<PlayerSource>> {
    val result = mutableListOf<PlayerSource>()

    return try {
        val tempServer = doc.getElementById("changeServer")?.select("option")
        for (i in 0 until tempServer?.size!!) {
            val tempValue = tempServer.eq(i).attr("value")
            if (!tempValue.equals("kuramadrive") && !tempValue.equals("mega")
                && !tempValue.equals("auto") && !tempValue.equals("streamsb")
            ) {
                if (tempValue.contains("archive") || tempValue.contains("kurama")) {
                    result.addAll(videoFromServer(url, tempValue))
                }
            }
        }

        if (result.isEmpty()) Resource.empty()
        else Resource.success(result)

    } catch (e: Exception) {
        debug { "Exception: ${e.localizedMessage}" }
        when (e) {
            is TimeoutException -> Resource.fail(e.message.toString())
            else -> Resource.fail(e.localizedMessage)
        }
    }
}

private suspend fun videoFromServer(url: String, value: String): List<PlayerSource> {
    val jsoup = withContext(Dispatchers.IO) {
        Jsoup
            .connect("$url?activate_stream=1&stream_server=$value")
            .get()
    }

    val result = mutableListOf<PlayerSource>()
    val event = jsoup.getElementsByTag("source")
    for (i in 0 until event.size) {
        val source = event.eq(i).attr("src")
        val resolution = event.eq(i).attr("size")

        val data = PlayerSource(url = source, resolution = resolution, server = value)
        result.add(data)
        debug { "SERVER VIDEO -> $data" }
    }

    return result
}

private suspend fun parseArchive(url: String): String {

    val jsoup = withContext(Dispatchers.IO) {
        Jsoup.connect(url)
            .followRedirects(true).execute()
    }

    debug { "URL -> $url" }
    debug { "Get Header Location : ${jsoup.header("Location")}" }
    debug { "Get Header Location : ${jsoup.url()}" }

    return jsoup.url().toString()
}