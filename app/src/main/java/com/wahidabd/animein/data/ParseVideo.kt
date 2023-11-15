package com.wahidabd.animein.data

import com.wahidabd.animein.utils.Constant
import com.wahidabd.animein.utils.Constant.USER_AGENT
import com.wahidabd.library.data.Resource
import com.wahidabd.library.utils.common.emptyString
import com.wahidabd.library.utils.extensions.debug
import org.jsoup.Connection
import org.jsoup.Jsoup
import java.util.concurrent.TimeoutException


/**
 * Created by Wahid on 7/15/2023.
 * Github github.com/wahidabd.
 */

fun kuramanime(url: String): Resource<String>{

    return try {
        val jsoup = Jsoup.connect(url).get()
        val result = jsoup.getElementsByTag("source")
        val size = result.size

        for (i in 0 until size){
            debug { "Kuramanime ${result.eq(i)}" }
        }

        Resource.success("OK")
    }catch (e: Exception){
        when (e) {
            is TimeoutException -> Resource.fail(e.message.toString())
            else -> Resource.fail("Unknown Error Exception")
        }
    }

}

fun mediafire(url: String): Resource<String> {

    return try {
        val jsoup = Jsoup.connect(url).userAgent(USER_AGENT).get()
        val result = jsoup.getElementById("downloadButton")!!.attr("href")
        Resource.success(result)
    }catch (e: Exception){
        when (e) {
            is TimeoutException -> Resource.fail(e.message.toString())
            else -> Resource.fail("Unknown Error Exception")
        }
    }


}

fun wibuFile(url: String): Resource<String> {
    val jsoup = Jsoup.connect(url).get()
    val tag = jsoup.getElementsByTag("script")
    var result = emptyString()

    return try {
        for (i in tag) {
            result = if (i.data().contains("sources")) {
                i.data().substringAfter("sources: [{").substringBefore("}],")
                    .substringAfter("src: \"").substringBefore(",").replace("\"", "")
            } else {
                "File not found"
            }
        }

        Resource.success(result)
    } catch (e: Exception) {
        when (e) {
            is TimeoutException -> Resource.fail(e.message.toString())
            else -> Resource.fail("Unknown Error Exception")
        }
    }

}


fun blogger(url: String){

    val jsoup = Jsoup.connect(url).method(Connection.Method.GET).execute()
    jsoup.body()
        .takeIf { !it.contains("errorContainer") }!!
        .substringAfter("\"streams\":[")
        .substringBefore("]")
        .split("},")
        .map {
            val videoUrl = it.substringAfter("{\"play_url\":\"").substringBefore('"')
            val format = it.substringAfter("\"format_id\":").substringBefore("}")
            val quality = when (format) {
                "18" -> "360p"
                "22" -> "720p"
                else -> "Unknown"
            }
            debug { "VIDEO -> $videoUrl $format $quality" }
        }

}


fun krakenVideo(url: String): Resource<String> {
    return try {
        val jsoup = Jsoup.connect(url).get()
        val url = jsoup.getElementsByTag("source").attr("src").replaceFirst("//", "https://")

        Resource.success(url)
    }catch (e: Exception){
        when (e) {
            is TimeoutException -> Resource.fail(e.message.toString())
            else -> Resource.fail("Unknown Error Exception")
        }
    }

}

fun krakenFile(url: String): Resource<String> {
    val krakenJsoup = Jsoup.connect(url).get()
    val krakenUrl = krakenJsoup.select("form").attr("action").replace("//", "https://")
    val token = krakenJsoup.select("form > input").attr("value")
    debug { "$krakenUrl --> $token" }

    return try {
        val jsoup = Jsoup.connect(krakenUrl)
            .data("token", token)
            .userAgent(USER_AGENT)
            .method(Connection.Method.POST)
            .ignoreContentType(true)
            .execute()


        val result = jsoup.body().substringAfter("\"url\":\"")
            .substringBefore("\"}").replace("\\", "")
        Resource.success(result)
    } catch (e: Exception) {
        when (e) {
            is TimeoutException -> Resource.fail(e.message.toString())
            else -> Resource.fail("Unknown Error Exception")
        }
    }
}

fun hxFile(url: String): Resource<String> {
    val jsoup = Jsoup.connect(url)
        .get()

    val hxUrl = jsoup.getElementsByTag("textarea")[0].text()

    return try {
        val base = "https://hxfile.co/"
        val id = hxUrl.replace(base, "")
        val hxJsoup = Jsoup.connect(hxUrl)
            .data("op", "download2")
            .data("id", id)
            .userAgent("Mozilla")
            .post()

        Resource.success(hxJsoup.select("div.download-button > a").attr("href"))
    } catch (e: Exception) {
        when (e) {
            is TimeoutException -> Resource.fail(e.message.toString())
            else -> Resource.fail("Unknown Error Exception")
        }
    }
}