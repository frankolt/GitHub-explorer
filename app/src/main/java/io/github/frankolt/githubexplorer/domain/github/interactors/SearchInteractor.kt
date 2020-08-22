package io.github.frankolt.githubexplorer.domain.github.interactors

import io.github.frankolt.githubexplorer.data.http.github.GitHubService
import io.github.frankolt.githubexplorer.domain.github.mappers.RepositorySearchResultMapper
import io.github.frankolt.githubexplorer.domain.github.models.RepositorySearchResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchInteractor @Inject constructor(
    private val gitHubService: GitHubService
) {

    suspend fun execute(query: String): RepositorySearchResult = withContext(Dispatchers.IO) {
        RepositorySearchResultMapper.fromResponse(gitHubService.searchRepositories(query))
    }
}
