package me.uptop.testmvpsample.dagger.modules

import android.app.Activity
import android.content.Context

import dagger.Module
import dagger.Provides
import me.uptop.testmvpsample.dagger.ActivityContext

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @ActivityContext
    internal fun providesContext(): Context {
        return activity
    }
}
