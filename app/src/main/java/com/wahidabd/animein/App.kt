package com.wahidabd.animein

import com.wahidabd.animein.di.animeModule
import com.wahidabd.animein.di.playerModule
import com.wahidabd.library.presentation.BaseApplication
import org.koin.core.module.Module
import timber.log.Timber


/**
 * Created by Wahid on 7/9/2023.
 * Github github.com/wahidabd.
 */


class App : BaseApplication() {
    override fun getDefineModule(): List<Module> {
        return listOf(
            animeModule,
            playerModule
        )
    }

    override fun initApp() {
        Timber.plant(Timber.DebugTree())
    }

}