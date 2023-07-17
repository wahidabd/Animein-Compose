package com.wahidabd.animein.screen.anime

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wahidabd.animein.domain.anime.AnimeUseCase
import com.wahidabd.animein.domain.anime.model.Anime
import com.wahidabd.animein.domain.anime.model.AnimeDetail
import com.wahidabd.animein.domain.anime.model.Episode
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
 * Created by Wahid on 7/16/2023.
 * Github github.com/wahidabd.
 */


@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val useCase: AnimeUseCase
) : ViewModel() {

    private val _anime = mutableStateOf<Flow<PagingData<Anime>>>(emptyFlow())
    val anime: State<Flow<PagingData<Anime>>> get() = _anime

    private val _detail = MutableStateFlow<Resource<AnimeDetail>>(Resource.loading())
    val detail: StateFlow<Resource<AnimeDetail>> get() = _detail

    private val _episode = mutableStateOf<Flow<PagingData<Episode>>>(emptyFlow())
    val episode: State<Flow<PagingData<Episode>>> get() = _episode


    fun anime(q: String){
        viewModelScope.launch {
            _anime.value = useCase.anime(q)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
        }
    }

    fun detail(slug: String){
        viewModelScope.launch {
            useCase.detail(slug)
                .onEach { _detail.value = it }
                .launchIn(viewModelScope)
        }
    }

    fun episode(slug: String){
        viewModelScope.launch {
            _episode.value = useCase.episode(slug)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
        }
    }
}