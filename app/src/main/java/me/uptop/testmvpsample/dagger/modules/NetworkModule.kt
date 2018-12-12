package me.uptop.testmvpsample.dagger.modules

import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import me.uptop.testmvpsample.data.network.NetworkHelper
import me.uptop.testmvpsample.data.network.RestService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        return createClient()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return createRetrofit(okHttpClient)
    }

    @Provides
    @Singleton
    internal fun provideRestService(retrofit: Retrofit): RestService {
        return retrofit.create(RestService::class.java)
    }

    private fun createClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(NetworkHelper.MAX_CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .readTimeout(NetworkHelper.MAX_READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(NetworkHelper.MAX_WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .build()
    }

    private fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(NetworkHelper.BASE_URL)
                .addConverterFactory(createConvertFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    private fun createConvertFactory(): Converter.Factory {
        return GsonConverterFactory.create(GsonBuilder().create())
    }
}

