package com.ahmedorabi.githubapp.di

import android.app.Application
import androidx.room.Room
import com.ahmedorabi.githubapp.data.api.ApiService
import com.ahmedorabi.githubapp.data.api.MockInterceptor
import com.ahmedorabi.githubapp.data.db.AppDatabase
import com.ahmedorabi.githubapp.data.db.RepoDao
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class AppModule {

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }


    @Provides
    @Named("realOkHttpClient")
    fun provideHttpClientReal(): OkHttpClient {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

    }

    @Singleton
    @Provides
    @Named("realRetrofit")
    fun provideRetrofitReal(gson: Gson, @Named("realOkHttpClient") client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Named("realApiService")
    fun provideApiServiceReal(@Named("realRetrofit") retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    @Named("MockInterceptor")
    fun provideMockInterceptor(): Interceptor = MockInterceptor()

    @Provides
    @Singleton
    @Named("MockOkHttpClient")
    fun provideOkHttpClient(@Named("MockInterceptor") authInterceptor: Interceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(authInterceptor)
            .build()

    @Provides
    @Singleton
    @Named("MockRetrofit")
    fun provideRetrofit(
        @Named("MockOkHttpClient")
        client: OkHttpClient,
        gson: Gson
    ): Retrofit = Retrofit
        .Builder()
        .client(client)
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    @Provides
    @Named("MockApiService")
    fun provideApiServiceMock(
        @Named("MockRetrofit")
        retrofit: Retrofit
    ): ApiService {
        return retrofit.create(ApiService::class.java)
    }


    @Singleton
    @Provides
    fun provideDb(app: Application): AppDatabase {

        return Room.databaseBuilder(app, AppDatabase::class.java, "repos.db")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideRepoDao(db: AppDatabase): RepoDao {
        return db.repoDao()
    }

}