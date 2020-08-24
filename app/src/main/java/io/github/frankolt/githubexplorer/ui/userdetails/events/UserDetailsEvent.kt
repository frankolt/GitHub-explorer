package io.github.frankolt.githubexplorer.ui.userdetails.events

sealed class UserDetailsEvent {

    class OpenInBrowser(
        val url: String
    ) : UserDetailsEvent()
}
