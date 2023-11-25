package com.wahidabd.animeku.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wahidabd.animeku.domain.anime.AnimeUseCase
import com.wahidabd.animeku.domain.anime.model.Anime
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
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */


@HiltViewModel
class HomeViewModel @Inject constructor(private val useCase: AnimeUseCase) : ViewModel() {

    private val _spotLight = MutableStateFlow<Resource<List<Anime>>>(Resource.loading())
    val spotLight: StateFlow<Resource<List<Anime>>> get() = _spotLight

    private val _populars = MutableStateFlow<Resource<List<Anime>>>(Resource.loading())
    val populars: StateFlow<Resource<List<Anime>>> get() = _populars

    private val _movies = MutableStateFlow<Resource<List<Anime>>>(Resource.loading())
    val movies: StateFlow<Resource<List<Anime>>> get() = _movies

    private val _ongoings = MutableStateFlow<Resource<List<Anime>>>(Resource.loading())
    val ongoings: StateFlow<Resource<List<Anime>>> get() = _ongoings

    private val _finished = MutableStateFlow<Resource<List<Anime>>>(Resource.loading())
    val finished: StateFlow<Resource<List<Anime>>> get() = _finished

    private val _paging = mutableStateOf<Flow<PagingData<Anime>>>(emptyFlow())
    val paging: State<Flow<PagingData<Anime>>> get() = _paging

    fun initViewModel(){
        spotLight()
        populars()
        movies()
        ongoings()
        finished()
    }

    private fun combine(){

    }

    private fun spotLight(){
        viewModelScope.launch {
            useCase.spotLight()
                .onEach { _spotLight.value = it }
                .launchIn(viewModelScope)
        }
    }

    private fun populars(){
        viewModelScope.launch {
            useCase.populars()
                .onEach { _populars.value = it }
                .launchIn(viewModelScope)
        }
    }

    private fun movies(){
        viewModelScope.launch {
            useCase.movies()
                .onEach { _movies.value = it }
                .launchIn(viewModelScope)
        }
    }

    private fun ongoings(){
        viewModelScope.launch {
            useCase.ongoings()
                .onEach { _ongoings.value = it }
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

    fun paging(q: String){
        viewModelScope.launch {
            _paging.value = useCase.animeList(q)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
        }
    }

}