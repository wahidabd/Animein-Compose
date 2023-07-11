package com.wahidabd.animein.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.airbnb.lottie.model.MutablePair
import com.wahidabd.animein.data.anime.model.AnimeResponse
import com.wahidabd.animein.domain.anime.AnimeUseCase
import com.wahidabd.animein.domain.anime.model.Carousel
import com.wahidabd.library.data.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


class HomeViewModel(private val useCase: AnimeUseCase) : ViewModel() {

    private val _anime = mutableStateOf<Flow<PagingData<AnimeResponse>>>(emptyFlow())
    val anime: State<Flow<PagingData<AnimeResponse>>> get() = _anime

    private val _carousel = MutableStateFlow<Resource<List<Carousel>>>(Resource.loading())
    val carousel: StateFlow<Resource<List<Carousel>>> get() = _carousel



    init {
        anime()
        carousel()
    }

    fun anime() {
        viewModelScope.launch {
            _anime.value = useCase.anime()
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
        }
    }

    fun carousel() {
        useCase.carousel()
            .onEach { _carousel.value = it }
            .launchIn(viewModelScope)
    }

}