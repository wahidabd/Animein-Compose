package com.wahidabd.animein.di

import com.wahidabd.animein.data.anime.AnimeDatSource
import com.wahidabd.animein.data.anime.AnimeRepository
import com.wahidabd.animein.domain.anime.AnimeInteractor
import com.wahidabd.animein.domain.anime.AnimeUseCase
import com.wahidabd.animein.ui.screen.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


val animeModule = module {
    single<AnimeRepository> { AnimeDatSource() }
    single<AnimeUseCase> { AnimeInteractor(get()) }
    viewModel { HomeViewModel(get()) }
}