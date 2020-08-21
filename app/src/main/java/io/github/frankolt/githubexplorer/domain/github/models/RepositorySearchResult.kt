package io.github.frankolt.githubexplorer.domain.github.models

data class RepositorySearchResult(
    val totalCount: Long? = null,
    val areResultsIncomplete: Boolean? = null,
    val items: List<Repository>? = null
)
