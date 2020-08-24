package io.github.frankolt.githubexplorer.ui.repositorysearch.state

import io.github.frankolt.githubexplorer.domain.github.models.Repository

sealed class QueryState {

    object Empty : QueryState()

    object Loading : QueryState()

    data class Loaded(
        val items: List<Repository>,
        val paginationState: PaginationState = PaginationState.None
    ) : QueryState()

    object Error : QueryState()
}
