package me.uptop.testmvpsample.dagger.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    internal fun providesApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    internal fun providesContext(): Context {
        return application
    }

}
