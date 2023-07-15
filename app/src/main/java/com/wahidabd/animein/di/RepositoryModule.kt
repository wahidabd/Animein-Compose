package com.wahidabd.animein.di

import com.wahidabd.animein.data.anime.AnimeDataSource
import com.wahidabd.animein.data.anime.AnimeRepository
import com.wahidabd.animein.data.player.PlayerDataSource
import com.wahidabd.animein.data.player.PlayerRepository
import com.wahidabd.animein.domain.anime.AnimeInteractor
import com.wahidabd.animein.domain.anime.AnimeUseCase
import com.wahidabd.animein.domain.player.PlayerInteractor
import com.wahidabd.animein.domain.player.PlayerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Wahid on 7/15/2023.
 * Github github.com/wahidabd.
 */


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideAnimeDataSource(): AnimeRepository = AnimeDataSource()

    @Singleton
    @Provides
    fun provideAnimeInteractor(repository: AnimeRepository): AnimeUseCase =
        AnimeInteractor(repository)

    @Singleton
    @Provides
    fun providePlayerDataSource(): PlayerRepository = PlayerDataSource()

    @Singleton
    @Provides
    fun providePlayerInteractor(repository: PlayerRepository): PlayerUseCase =
        PlayerInteractor(repository)

}