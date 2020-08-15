package io.github.frankolt.githubexplorer.data.http.github

import okhttp3.Interceptor

class GitHubRequestInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain) = chain.request().newBuilder()
        .addHeader(GIT_HUB_HEADER_ACCEPT_KEY, GIT_HUB_HEADER_ACCEPT_VALUE)
        .build()
        .let { chain.proceed(it) }
}
