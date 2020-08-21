package io.github.frankolt.githubexplorer.domain.github.mappers

import io.github.frankolt.githubexplorer.data.http.github.models.RepositorySearchResultResponse
import io.github.frankolt.githubexplorer.domain.github.models.RepositorySearchResult

object RepositorySearchResultMapper {

    fun fromResponse(response: RepositorySearchResultResponse) = RepositorySearchResult(
        response.totalCount,
        response.areResultsIncomplete,
        response.items?.map { RepositoryMapper.fromResponse(it) }
    )
}
