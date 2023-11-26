package com.wahidabd.animeku.di

import android.content.Context
import com.wahidabd.animeku.data.local.AppDatabase
import com.wahidabd.animeku.data.local.anime.AnimeLocalDataSource
import com.wahidabd.animeku.data.local.anime.AnimeLocalRepository
import com.wahidabd.animeku.data.remote.anime.AnimeDataSource
import com.wahidabd.animeku.data.remote.anime.AnimeRepository
import com.wahidabd.animeku.domain.anime.local.AnimeLocalInteractor
import com.wahidabd.animeku.domain.anime.local.AnimeLocalUseCase
import com.wahidabd.animeku.domain.anime.remote.AnimeInteractor
import com.wahidabd.animeku.domain.anime.remote.AnimeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun injectAnimeInteractor(repository: AnimeRepository): AnimeUseCase =
        AnimeInteractor(repository)

    @Provides
    @Singleton
    fun injectDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getDatabase(context)

    @Singleton
    @Provides
    fun injectAnimeLocalSource(db: AppDatabase): AnimeLocalRepository =
        AnimeLocalDataSource(db)

    @Singleton
    @Provides
    fun injectAnimeLocalInteractor(repository: AnimeLocalRepository): AnimeLocalUseCase =
        AnimeLocalInteractor(repository)
}