package io.github.frankolt.githubexplorer.data.sources.http.github.search

import io.github.frankolt.githubexplorer.data.sources.http.github.models.SearchResult
import io.github.frankolt.githubexplorer.data.sources.http.github.GitHubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val gitHubService: GitHubService
) {

    suspend fun execute(query: String): SearchResult = withContext(Dispatchers.IO) {
        gitHubService.search(query)
    }
}
