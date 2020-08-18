package io.github.frankolt.githubexplorer.domain.github.models

data class SearchResult(
    val totalCount: Int? = null,
    val areResultsIncomplete: Boolean? = null,
    val items: List<SearchResultItem>? = null
)
