package com.wahidabd.animeku.ui.screen.anime

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.wahidabd.animeku.domain.anime.model.Anime
import com.wahidabd.animeku.domain.anime.remote.AnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject


/**
 * Created by wahid on 12/6/2023.
 * Github github.com/wahidabd.
 */


@HiltViewModel
class AnimeViewModel @Inject constructor(
    private val useCase: AnimeUseCase
) : ViewModel() {

    private val _paging = mutableStateOf<Flow<PagingData<Anime>>>(emptyFlow())
    val paging: State<Flow<PagingData<Anime>>> get() = _paging

    suspend fun paging(q: String) {
        _paging.value = useCase.animeList(q)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
    }
}