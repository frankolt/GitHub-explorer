package io.github.frankolt.githubexplorer.ui.userdetails.state

import io.github.frankolt.githubexplorer.domain.github.models.User

sealed class UserDetailsState {

    object Loading : UserDetailsState()

    class Loaded(
        val user: User
    ) : UserDetailsState()

    object Error : UserDetailsState()
}
