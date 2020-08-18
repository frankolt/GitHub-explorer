package io.github.frankolt.githubexplorer.data.sources.http.github.interactors

import io.github.frankolt.githubexplorer.data.sources.http.github.models.SearchResultResponse
import io.github.frankolt.githubexplorer.data.sources.http.github.GitHubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val gitHubService: GitHubService
) {

    suspend fun execute(query: String): SearchResultResponse = withContext(Dispatchers.IO) {
        gitHubService.search(query)
    }
}
