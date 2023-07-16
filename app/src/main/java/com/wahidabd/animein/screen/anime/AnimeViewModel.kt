package com.wahidabd.animein.screen.anime

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wahidabd.animein.domain.anime.AnimeUseCase
import com.wahidabd.animein.domain.anime.model.Anime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
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


    fun anime(q: String){
        viewModelScope.launch {
            _anime.value = useCase.anime(q)
                .distinctUntilChanged()
                .cachedIn(viewModelScope)
        }
    }
}