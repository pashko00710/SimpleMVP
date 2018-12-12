package me.uptop.testmvpsample.dagger.components

import android.app.Application
import android.content.Context
import dagger.Component
import me.uptop.testmvpsample.dagger.modules.ActivityModule
import me.uptop.testmvpsample.dagger.modules.ApplicationModule
import me.uptop.testmvpsample.dagger.modules.LocalModule
import me.uptop.testmvpsample.dagger.modules.NetworkModule
import me.uptop.testmvpsample.data.DataManager
import me.uptop.testmvpsample.data.PreferenceManager
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, LocalModule::class])
interface AppComponent {
    //    @ApplicationContext
    fun context(): Context

    fun application(): Application

    fun addActivityComponent(activityModule: ActivityModule): ActivityComponent

    fun providesDataManager(): DataManager

    fun provideSharedPreferences(): PreferenceManager

}
