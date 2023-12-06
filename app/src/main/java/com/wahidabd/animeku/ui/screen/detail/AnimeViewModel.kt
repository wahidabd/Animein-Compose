package com.wahidabd.animeku.ui.screen.detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wahidabd.animeku.domain.anime.local.AnimeLocalUseCase
import com.wahidabd.animeku.domain.anime.model.Anime
import com.wahidabd.animeku.domain.anime.model.AnimeDetail
import com.wahidabd.animeku.domain.anime.model.Episode
import com.wahidabd.animeku.domain.anime.remote.AnimeUseCase
import com.wahidabd.library.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by wahid on 11/19/2023.
 * Github github.com/wahidabd.
 */

@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val useCase: AnimeUseCase,
    private val bookmarkUseCase: AnimeLocalUseCase
) : ViewModel() {

    val bookmark = mutableStateOf(false)

    private val _anime = MutableStateFlow<Resource<AnimeDetail>>(Resource.loading())
    val anime: StateFlow<Resource<AnimeDetail>> get() = _anime

    private val _episode = MutableStateFlow<Resource<List<Episode>>>(Resource.loading())
    val episode: StateFlow<Resource<List<Episode>>> get() = _episode

    private val _paging = mutableStateOf<Flow<PagingData<Anime>>>(emptyFlow())
    val paging: State<Flow<PagingData<Anime>>> get() = _paging

    fun getAnimeDetail(slug: String) {
        getBookmark(slug)
        viewModelScope.launch {
            useCase.detail(slug)
                .onEach { _anime.value = it }
                .launchIn(viewModelScope)
        }
    }

    fun getEpisodes(slug: String) {
        viewModelScope.launch {
            useCase.episode(slug)
                .onEach { _episode.value = it }
                .launchIn(viewModelScope)
        }
    }

    private fun getBookmark(slug: String) {
        viewModelScope.launch {
            bookmarkUseCase.get(slug)
                ?.onEach { bookmark.value = it.slug == slug }
                ?.launchIn(viewModelScope)
        }
    }

    fun bookmark(anime: AnimeDetail) {
        viewModelScope.launch {
            if (bookmark.value) {
                bookmarkUseCase.delete(anime)
            } else {
                bookmarkUseCase.save(anime)
            }
        }
    }
    fun paging(endpoint: String){
        viewModelScope.launch {
            _paging.value = useCase.animeList(endpoint)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
        }
    }

}