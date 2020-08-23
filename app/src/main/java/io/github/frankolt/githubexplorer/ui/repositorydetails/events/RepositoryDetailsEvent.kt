package io.github.frankolt.githubexplorer.ui.repositorydetails.events

sealed class RepositoryDetailsEvent {

    class Error(
        val message: String
    ) : RepositoryDetailsEvent()

    class OpenInBrowser(
        val url: String
    ) : RepositoryDetailsEvent()

    class OpenUserDetails(
        val username: String
    ) : RepositoryDetailsEvent()
}
