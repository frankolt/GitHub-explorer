package io.github.frankolt.githubexplorer.ui.userdetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import io.github.frankolt.githubexplorer.domain.github.interactors.UserInteractor

class UserDetailsViewModel @ViewModelInject constructor(
    private val userInteractor: UserInteractor
) : ViewModel() {
}
