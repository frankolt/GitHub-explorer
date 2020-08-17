package io.github.frankolt.githubexplorer.data.sources.http.github.di

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.github.frankolt.githubexplorer.BuildConfig
import io.github.frankolt.githubexplorer.data.sources.http.github.GitHubRequestInterceptor
import io.github.frankolt.githubexplorer.data.sources.http.github.GitHubService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object GitHubModule {

    @Provides
    @Singleton
    @GitHub
    fun provideOkHttpClient(
        okHttpClient: OkHttpClient,
        gitHubRequestInterceptor: GitHubRequestInterceptor
    ): OkHttpClient = okHttpClient.newBuilder()
        .addInterceptor(gitHubRequestInterceptor)
        .build()

    @Provides
    @Singleton
    @GitHub
    fun provideRetrofit(
        @GitHub okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.GIT_HUB_API_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    @Provides
    @Singleton
    fun provideGitHubService(
        @GitHub retrofit: Retrofit
    ): GitHubService = retrofit.create(GitHubService::class.java)
}
