package com.wahidabd.animeku.ui.screen.anime

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.animeku.domain.anime.AnimeUseCase
import com.wahidabd.animeku.domain.anime.model.AnimeDetail
import com.wahidabd.animeku.domain.anime.model.Episode
import com.wahidabd.library.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by wahid on 11/19/2023.
 * Github github.com/wahidabd.
 */

@HiltViewModel
class AnimeViewModel @Inject constructor(private val useCase: AnimeUseCase) : ViewModel() {

    private val _anime = MutableStateFlow<Resource<AnimeDetail>>(Resource.loading())
    val anime: StateFlow<Resource<AnimeDetail>> get() = _anime

    private val _episode = MutableStateFlow<Resource<List<Episode>>>(Resource.loading())
    val episode: StateFlow<Resource<List<Episode>>> get() = _episode

    fun getAnimeDetail(slug: String) {
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
}