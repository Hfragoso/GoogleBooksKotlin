package com.example.googlebooks_kotlin.di

import androidx.lifecycle.ViewModelProvider
import com.example.googlebooks_kotlin.api.BooksService
import com.example.googlebooks_kotlin.dataModel.BooksRepository
import com.example.googlebooks_kotlin.utils.Constants.BASE_URL
import com.example.googlebooks_kotlin.viewModel.ViewModelFactory
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class UtilsModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        val builder = GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        return builder.setLenient().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun getApiCallInterface(retrofit: Retrofit): BooksService {
        return retrofit.create(BooksService::class.java)
    }

    @Provides
    @Singleton
    fun getRepository(booksService: BooksService): BooksRepository {
        return BooksRepository(booksService)
    }

    @Provides
    @Singleton
    fun getHttpClient(): OkHttpClient {
        val myLogger = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .addInterceptor(myLogger).build()
    }

    @Provides
    @Singleton
    internal fun getViewModelFactory(myRepository: BooksRepository): ViewModelProvider.Factory {
        return ViewModelFactory(myRepository)
    }
}


