package me.uptop.testmvpsample

import android.app.Application
import io.realm.Realm
import me.uptop.testmvpsample.dagger.components.AppComponent
import me.uptop.testmvpsample.dagger.components.DaggerAppComponent
import me.uptop.testmvpsample.dagger.modules.ApplicationModule


class TestSampleApp : Application() {
    val component: AppComponent
        get() {
            if (applicationComponent == null) {
                applicationComponent = DaggerAppComponent.builder()
                        .applicationModule(ApplicationModule(this))
                        .build()
            }

            return applicationComponent!!
        }

    override fun onCreate() {
        super.onCreate()
        Realm.init(applicationContext)
        application = this
    }

    companion object {
        var applicationComponent: AppComponent? = null
        lateinit var application: TestSampleApp
    }
}
