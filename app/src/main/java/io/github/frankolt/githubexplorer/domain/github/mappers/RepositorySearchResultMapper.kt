package io.github.frankolt.githubexplorer.domain.github.mappers

import io.github.frankolt.githubexplorer.data.http.github.models.RepositorySearchResultResponse
import io.github.frankolt.githubexplorer.domain.github.models.RepositorySearchResult

object RepositorySearchResultMapper {

    /**
     * Maps the `RepositorySearchResultResponse` model to the `RepositorySearchResult` model. If
     * mandatory fields are missing from the repository items, they are left out.
     */
    @Throws(NullPointerException::class)
    fun fromResponse(response: RepositorySearchResultResponse) = RepositorySearchResult(
        response.totalCount,
        response.areResultsIncomplete,
        response.items?.mapNotNull {
            try {
                RepositoryMapper.fromResponse(it)
            } catch (e: NullPointerException) {
                null
            }
        }
    )
}
