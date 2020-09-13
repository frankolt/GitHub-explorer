package io.github.frankolt.githubexplorer.domain.github.interactors.repositorysearch

import io.github.frankolt.githubexplorer.data.http.github.GitHubService
import io.github.frankolt.githubexplorer.domain.github.interactors.AsyncResult
import io.github.frankolt.githubexplorer.domain.github.mappers.repositorysearchresult.RepositorySearchResultMapper
import io.github.frankolt.githubexplorer.domain.github.models.RepositorySearchResult
import java.lang.Exception
import javax.inject.Inject

class RepositorySearchInteractor @Inject constructor(
    private val gitHubService: GitHubService,
    private val repositorySearchResultMapper: RepositorySearchResultMapper
) {

    private var lastQuery: String? = null
    private var lastRequestedPage = GIT_HUB_FIRST_PAGE
    private var isLastPageReached = false
    private var isPaginationInProgress = false

    suspend fun load(query: String): AsyncResult<RepositorySearchResult> {
        lastRequestedPage = GIT_HUB_FIRST_PAGE
        isLastPageReached = false
        lastQuery = query
        try {
            val result = gitHubService.searchRepositories(
                query,
                page = GIT_HUB_FIRST_PAGE,
                perPage = GIT_HUB_ITEMS_PER_PAGE
            )
            return AsyncResult.Success(repositorySearchResultMapper.map(result))
        } catch (e: Exception) {
            return AsyncResult.Failure(e)
        }
    }

    @Throws(RequestInProgressException::class, LastPageReachedException::class)
    suspend fun loadNextPage(): AsyncResult<RepositorySearchResult> {
        if (isPaginationInProgress) {
            throw RequestInProgressException()
        }
        if (isLastPageReached) {
            throw LastPageReachedException()
        }
        isPaginationInProgress = true
        val query = lastQuery ?: throw IllegalStateException("No last query.")
        try {
            val result = gitHubService.searchRepositories(
                query,
                page = lastRequestedPage + 1,
                perPage = GIT_HUB_ITEMS_PER_PAGE
            )
            isPaginationInProgress = false
            if (result.items.isNullOrEmpty()) {
                isLastPageReached = true
            } else {
                ++lastRequestedPage
            }
            return AsyncResult.Success(repositorySearchResultMapper.map(result))
        } catch (e: Exception) {
            isPaginationInProgress = false
            return AsyncResult.Failure(e)
        }
    }
}
