package com.test.githubuser.core.di

import androidx.room.Room
import com.test.githubuser.BuildConfig
import com.test.githubuser.core.data.GithubRepository
import com.test.githubuser.core.data.source.local.LocalDataSource
import com.test.githubuser.core.data.source.local.room.GithubDatabase
import com.test.githubuser.core.data.source.remote.RemoteDataSource
import com.test.githubuser.core.data.source.remote.network.GithubService
import com.test.githubuser.core.domain.repositroy.IGithubRepository
import com.test.githubuser.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<GithubDatabase>().searchDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            GithubDatabase::class.java,
            "Github.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(GithubService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IGithubRepository> {
        GithubRepository(get(), get(), get())
    }
}