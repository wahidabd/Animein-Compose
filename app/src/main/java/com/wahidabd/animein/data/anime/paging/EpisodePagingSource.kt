package com.wahidabd.animein.data.anime.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import com.wahidabd.animein.domain.anime.model.Episode
import com.wahidabd.animein.utils.Constant
import com.wahidabd.library.utils.extensions.debug
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException


/**
 * Created by Wahid on 7/17/2023.
 * Github github.com/wahidabd.
 */


class EpisodePagingSource(
    private val endpoint: String
) : PagingSource<Int, Episode>() {

    override fun getRefreshKey(state: PagingState<Int, Episode>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Episode> {
        return try {
            val position = params.key ?: 1

            val url = if (position == 1) "${Constant.BASE_URL}anime/$endpoint"
            else "${Constant.BASE_URL}anime/$endpoint?page=$position"

            val document = withContext(Dispatchers.IO) { Jsoup.connect(url).get() }
            val items = withContext(Dispatchers.IO){
                parseItem(document)
            }

            LoadResult.Page(
                data = items,
                prevKey = if (position == 1) null else position.minus(1),
                nextKey = if (items.isEmpty()) null else position.plus(1)
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    private fun parseItem(doc: Document): List<Episode> {
        val result = mutableListOf<Episode>()
        val parseToHtml = doc.getElementById("episodeLists")?.attr("data-content")


        val jsoup = Jsoup.parse(parseToHtml.toString())

        val el = jsoup.select("a")
        val size = el.size

        for (i in 0 until size) {
            if (!el.eq(i).text().contains("(")) {
                val name = el.eq(i).text().replace("Ep", "Episode")
                val slug = el.eq(i).attr("href")

                if (name.isNotEmpty() && slug.isNotEmpty()){
                    val episode = Episode(slug, name)
                    result.add(episode)
                }

            }
        }

        return result
    }
}