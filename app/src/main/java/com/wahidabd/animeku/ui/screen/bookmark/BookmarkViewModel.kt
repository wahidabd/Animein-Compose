package com.wahidabd.animeku.ui.screen.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.animeku.domain.anime.local.AnimeLocalUseCase
import com.wahidabd.animeku.domain.anime.model.Anime
import com.wahidabd.animeku.domain.anime.model.AnimeDetail
import com.wahidabd.library.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by wahid on 11/25/2023.
 * Github github.com/wahidabd.
 */


@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val useCase: AnimeLocalUseCase
) : ViewModel() {

    private val _anime = MutableStateFlow<AnimeDetail?>(null)
    val anime: StateFlow<AnimeDetail?> get() = _anime

    private val _animeList = MutableStateFlow<Resource<List<Anime>>>(Resource.loading())
    val animeList: StateFlow<Resource<List<Anime>>> get() = _animeList


    fun saveOrRemove(anime: AnimeDetail){
        viewModelScope.launch {
            if (anime.bookmark == true) useCase.delete(anime)
            else useCase.save(anime)
        }
    }

    fun save(anime: AnimeDetail) {
        viewModelScope.launch {
            useCase.save(anime)
        }
    }

    fun get(slug: String) {
        viewModelScope.launch {
            useCase.get(slug)
                ?.onEach { _anime.value = it }
                ?.launchIn(viewModelScope)
        }
    }

    fun getAll() {
        viewModelScope.launch {
            useCase.getAll()
                .onEach { anime -> _animeList.value = Resource.success(anime) }
                .onEmpty { _animeList.value = Resource.empty() }
                .launchIn(viewModelScope)
        }
    }

    fun delete(anime: AnimeDetail) {
        viewModelScope.launch {
            useCase.delete(anime)
        }
    }
}