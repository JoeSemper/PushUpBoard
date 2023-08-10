package com.joesemper.pushupboard

import android.app.Application
import com.joesemper.pushupboard.di.databaseModule
import com.joesemper.pushupboard.di.mainModule
import com.joesemper.pushupboard.di.repositoryModule
import com.joesemper.pushupboard.di.useCaseModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class PushUpApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@PushUpApp)
            modules(
                mainModule,
                databaseModule,
                repositoryModule,
                useCaseModule
            )
        }
    }
}