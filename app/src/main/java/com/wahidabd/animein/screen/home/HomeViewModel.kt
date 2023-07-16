package com.wahidabd.animein.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.animein.domain.anime.model.Anime
import com.wahidabd.animein.domain.anime.model.Carousel
import com.wahidabd.animein.domain.home.HomeUseCase
import com.wahidabd.library.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


@HiltViewModel
class HomeViewModel @Inject constructor (private val useCase: HomeUseCase) : ViewModel() {

//    private val _anime = mutableStateOf<Flow<PagingData<AnimeResponse>>>(emptyFlow())
//    val anime: State<Flow<PagingData<AnimeResponse>>> get() = _anime


    private val _movie = MutableStateFlow<Resource<List<Anime>>>(Resource.loading())
    val movie: StateFlow<Resource<List<Anime>>> get() = _movie


    private val _ongoing = MutableStateFlow<Resource<List<Anime>>>(Resource.loading())
    val ongoing: StateFlow<Resource<List<Anime>>> get() = _ongoing

    private val _finished = MutableStateFlow<Resource<List<Anime>>>(Resource.loading())
    val finished: StateFlow<Resource<List<Anime>>> get() = _finished


    private val _carousel = MutableStateFlow<Resource<List<Carousel>>>(Resource.loading())
    val carousel: StateFlow<Resource<List<Carousel>>> get() = _carousel

    fun initViewModel(){
        movie()
        finished()
        ongoing()
        carousel()
    }

    fun carousel(){
        viewModelScope.launch {
            useCase.carousel()
                .onEach { _carousel.value = it }
                .launchIn(viewModelScope)
        }
    }

    private fun movie(){
        viewModelScope.launch {
            useCase.movie()
                .onEach { _movie.value = it }
                .launchIn(viewModelScope)
        }
    }

    private fun ongoing(){
        viewModelScope.launch {
            useCase.ongoing()
                .onEach { _ongoing.value = it }
                .launchIn(viewModelScope)
        }
    }

    private fun finished(){
        viewModelScope.launch {
            useCase.finished()
                .onEach { _finished.value = it }
                .launchIn(viewModelScope)
        }
    }



}