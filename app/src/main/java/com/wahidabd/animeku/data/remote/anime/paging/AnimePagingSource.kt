package com.wahidabd.animeku.data.remote.anime.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import coil.network.HttpException
import com.wahidabd.animeku.data.remote.anime.dto.AnimeResponse
import com.wahidabd.animeku.data.remote.parseAnime
import com.wahidabd.animeku.data.remote.parseAnimeSearch
import com.wahidabd.animeku.utils.constants.Endpoints.ANIME_LIST
import com.wahidabd.animeku.utils.constants.Endpoints.BASE_URL
import com.wahidabd.animeku.utils.enums.PagingType
import com.wahidabd.library.utils.common.emptyString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import java.io.IOException


/**
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */


class AnimePagingSource(
    private val endpoint: String? = emptyString(),
    private val pagingType: PagingType = PagingType.NORMAL
) : PagingSource<Int, AnimeResponse>() {

    override fun getRefreshKey(state: PagingState<Int, AnimeResponse>): Int? =
        state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimeResponse> =
        try {
            val position = params.key ?: 1

            val url = when(pagingType) {
                PagingType.NORMAL -> if (position == 1) "${ANIME_LIST}$endpoint" else "${ANIME_LIST}page/$position/$endpoint"
                PagingType.SEARCH -> "${BASE_URL}page/$position/$endpoint"
                PagingType.GENRE -> "$endpoint/page/$position"
            }

            val document = withContext(Dispatchers.IO) { Jsoup.connect(url).get() }
            val items = if (pagingType == PagingType.NORMAL) parseAnime(document) else parseAnimeSearch(document)

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