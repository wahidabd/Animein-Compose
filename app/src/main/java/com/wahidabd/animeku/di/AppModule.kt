package com.wahidabd.animeku.di

import com.wahidabd.animeku.data.anime.AnimeDataSource
import com.wahidabd.animeku.data.anime.AnimeRepository
import com.wahidabd.animeku.domain.anime.AnimeInteractor
import com.wahidabd.animeku.domain.anime.AnimeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by wahid on 11/18/2023.
 * Github github.com/wahidabd.
 */


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectAnimeSource(): AnimeRepository = AnimeDataSource()

    @Singleton
    @Provides
    fun injectAnimeInteractor(repository: AnimeRepository): AnimeUseCase = AnimeInteractor(repository)

}