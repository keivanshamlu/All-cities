package com.shamlou.sample

import androidx.multidex.MultiDexApplication
import com.shamlou.sample.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class Application : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()


        // Init Koin
        startKoin {
            androidContext(this@Application)
            modules(koinModules)
        }
    }
}