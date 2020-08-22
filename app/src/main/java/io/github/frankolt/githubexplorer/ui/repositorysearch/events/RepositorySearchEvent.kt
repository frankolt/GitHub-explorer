package io.github.frankolt.githubexplorer.ui.repositorysearch.events

sealed class RepositorySearchEvent {

    class OpenUserDetails(
        val username: String
    ) : RepositorySearchEvent()
}
