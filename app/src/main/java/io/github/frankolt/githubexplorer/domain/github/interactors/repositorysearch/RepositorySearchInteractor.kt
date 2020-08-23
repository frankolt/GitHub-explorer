package io.github.frankolt.githubexplorer.domain.github.interactors.repositorysearch

import io.github.frankolt.githubexplorer.data.http.github.GitHubService
import io.github.frankolt.githubexplorer.domain.github.interactors.AsyncResult
import io.github.frankolt.githubexplorer.domain.github.mappers.RepositorySearchResultMapper
import io.github.frankolt.githubexplorer.domain.github.models.RepositorySearchResult
import java.lang.Exception
import javax.inject.Inject

class RepositorySearchInteractor @Inject constructor(
    private val gitHubService: GitHubService
) {

    private var lastQuery: String? = null
    private var lastRequestedPage = GIT_HUB_FIRST_PAGE
    private var isLastPageReached = false
    private var isRequestInProgress = false

    @Throws(RequestInProgressException::class)
    suspend fun load(query: String): AsyncResult<RepositorySearchResult> {
        if (isRequestInProgress) {
            throw RequestInProgressException()
        }
        lastRequestedPage = GIT_HUB_FIRST_PAGE
        isLastPageReached = false
        lastQuery = query
        isRequestInProgress = true
        try {
            val result = gitHubService.searchRepositories(
                query,
                page = GIT_HUB_FIRST_PAGE,
                perPage = GIT_HUB_ITEMS_PER_PAGE
            )
            isRequestInProgress = false
            return AsyncResult.Success(RepositorySearchResultMapper.fromResponse(result))
        } catch (e: Exception) {
            isRequestInProgress = false
            return AsyncResult.Failure(e)
        }
    }

    @Throws(RequestInProgressException::class, LastPageReachedException::class)
    suspend fun loadNextPage(): AsyncResult<RepositorySearchResult> {
        if (isRequestInProgress) {
            throw RequestInProgressException()
        }
        if (isLastPageReached) {
            throw LastPageReachedException()
        }
        isRequestInProgress = true
        val query = lastQuery ?: throw IllegalStateException("No last query.")
        val page = lastRequestedPage++
        try {
            val result = gitHubService.searchRepositories(
                query,
                page = page,
                perPage = GIT_HUB_ITEMS_PER_PAGE
            )
            isRequestInProgress = false
            if (result.items.isNullOrEmpty()) {
                isLastPageReached = true
            }
            return AsyncResult.Success(RepositorySearchResultMapper.fromResponse(result))
        } catch (e: Exception) {
            isRequestInProgress = false
            return AsyncResult.Failure(e)
        }
    }
}
