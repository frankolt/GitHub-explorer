package io.github.frankolt.githubexplorer.domain.github.mappers

import io.github.frankolt.githubexplorer.data.http.github.models.SearchResultResponse
import io.github.frankolt.githubexplorer.domain.github.models.SearchResult

object SearchResultMapper {

    fun fromResponse(response: SearchResultResponse) = SearchResult(
        response.totalCount,
        response.areResultsIncomplete,
        response.items?.map { SearchResultItemMapper.fromResponse(it) }
    )
}
