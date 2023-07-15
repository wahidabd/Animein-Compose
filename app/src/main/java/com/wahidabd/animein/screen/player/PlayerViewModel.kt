package com.wahidabd.animein.screen.player

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wahidabd.animein.domain.player.PlayerUseCase
import com.wahidabd.library.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject


/**
 * Created by Wahid on 7/10/2023.
 * Github github.com/wahidabd.
 */


@HiltViewModel
class PlayerViewModel @Inject constructor(private val useCase: PlayerUseCase) : ViewModel() {

    private val _player = MutableStateFlow<Resource<List<String>>>(Resource.loading())
    val player: StateFlow<Resource<List<String>>> get() = _player

    private val _videoUrl = MutableStateFlow<Resource<String>>(Resource.loading())
    val videoUrl: StateFlow<Resource<String>> get() = _videoUrl

    init {
        player()
    }

    fun videoUrl(url: String) {
        useCase.videoUrl(url)
            .onEach { _videoUrl.value = it }
            .launchIn(viewModelScope)
    }

    private fun player() {
        useCase.player()
            .onEach { _player.value = it }
            .launchIn(viewModelScope)
    }

}