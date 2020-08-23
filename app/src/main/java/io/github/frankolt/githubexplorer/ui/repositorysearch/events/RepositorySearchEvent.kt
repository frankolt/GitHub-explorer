package io.github.frankolt.githubexplorer.ui.repositorysearch.events

sealed class RepositorySearchEvent {

    class Error(
        val message: String
    ) : RepositorySearchEvent()

    class OpenUserDetails(
        val username: String
    ) : RepositorySearchEvent()

    class OpenRepositoryDetails(
        val owner: String,
        val repo: String
    ) : RepositorySearchEvent()
}
