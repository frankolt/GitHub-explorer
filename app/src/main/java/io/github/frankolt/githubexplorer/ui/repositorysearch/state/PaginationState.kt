package io.github.frankolt.githubexplorer.ui.repositorysearch.state

sealed class PaginationState {

    object None: PaginationState()

    object Loading : PaginationState()

    object Error : PaginationState()
}
