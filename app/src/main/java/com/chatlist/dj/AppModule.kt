package com.chatlist.dj

import android.app.Application
import android.content.Context
import com.chatlist.AppConstant.BASE_URL
import com.chatlist.data.ChatApi
import com.chatlist.data.PreferenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideChatApi(retrofit: Retrofit): ChatApi =
        retrofit.create(ChatApi::class.java)

    @Provides
    fun provideSharedRepository(application: Application): PreferenceRepository {
        return PreferenceRepository(
            application.getSharedPreferences("default_preferences", Context.MODE_PRIVATE)
        )
    }

}