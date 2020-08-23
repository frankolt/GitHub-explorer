package io.github.frankolt.githubexplorer.ui.userdetails.events

sealed class UserDetailsEvent {

    class Error(
        val message: String
    ) : UserDetailsEvent()

    class OpenInBrowser(
        val url: String
    ) : UserDetailsEvent()
}
