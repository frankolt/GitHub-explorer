package io.github.frankolt.githubexplorer.data.http.github

import okhttp3.Interceptor
import javax.inject.Inject

class GitHubRequestInterceptor @Inject constructor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain) = chain.request().newBuilder()
        .header(GIT_HUB_HEADER_USER_AGENT_KEY, GIT_HUB_HEADER_USER_AGENT_VALUE)
        .header(GIT_HUB_HEADER_ACCEPT_KEY, GIT_HUB_HEADER_ACCEPT_VALUE)
        .build()
        .let { chain.proceed(it) }
}
