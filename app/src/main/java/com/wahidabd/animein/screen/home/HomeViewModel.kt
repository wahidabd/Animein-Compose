package com.wahidabd.animein.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.animein.domain.anime.model.Anime
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


    private val _popular = MutableStateFlow<Resource<List<Anime>>>(Resource.loading())
    val popular: StateFlow<Resource<List<Anime>>> get() = _popular


    private val _newUpdate = MutableStateFlow<Resource<List<Anime>>>(Resource.loading())
    val newUpdate: StateFlow<Resource<List<Anime>>> get() = _newUpdate

    private val _newAdded = MutableStateFlow<Resource<List<Anime>>>(Resource.loading())
    val newAdded: StateFlow<Resource<List<Anime>>> get() = _newAdded

    fun initViewModel(){
        popular()
        newAdded()
        newUpdate()
    }

    private fun popular(){
        viewModelScope.launch {
            useCase.popular()
                .onEach { _popular.value = it }
                .launchIn(viewModelScope)
        }
    }

    private fun newUpdate(){
        viewModelScope.launch {
            useCase.newUpdate()
                .onEach { _newUpdate.value = it }
                .launchIn(viewModelScope)
        }
    }

    private fun newAdded(){
        viewModelScope.launch {
            useCase.newAdded()
                .onEach { _newAdded.value = it }
                .launchIn(viewModelScope)
        }
    }



}