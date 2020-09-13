package io.github.frankolt.githubexplorer.domain.github.interactors.repositorysearch

import io.github.frankolt.githubexplorer.domain.github.interactors.AsyncResult
import io.github.frankolt.githubexplorer.domain.github.interactors.Interactor
import io.github.frankolt.githubexplorer.domain.github.models.RepositorySearchResult

interface RepositorySearchInteractor :
    Interactor<RepositorySearchParameters, RepositorySearchResult> {

    @Throws(RequestInProgressException::class, LastPageReachedException::class)
    suspend fun loadNextPage(): AsyncResult<RepositorySearchResult>
}
