package me.uptop.testmvpsample.dagger.modules

import dagger.Module
import dagger.Provides
import me.uptop.testmvpsample.data.storage.RealmManager
import javax.inject.Singleton

@Module
class LocalModule {
    @Provides
    @Singleton
    internal fun provideRealmManager(): RealmManager {
        return RealmManager()
    }
}
