package com.wahidabd.animeku.ui.screen.search

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wahidabd.animeku.domain.anime.AnimeUseCase
import com.wahidabd.animeku.domain.anime.model.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by wahid on 11/25/2023.
 * Github github.com/wahidabd.
 */


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val useCase: AnimeUseCase
) : ViewModel() {

    var searchParam = mutableStateOf("")
    var previousSearch = mutableStateOf("")
    private val _anime = mutableStateOf<Flow<PagingData<Anime>>>(emptyFlow())
    val anime: State<Flow<PagingData<Anime>>> get() = _anime

    fun search(){
        viewModelScope.launch {
            if (searchParam.value.isNotEmpty()){
                _anime.value = useCase.search(searchParam.value)
                    .distinctUntilChanged()
                    .cachedIn(viewModelScope)
            }
        }
    }

}