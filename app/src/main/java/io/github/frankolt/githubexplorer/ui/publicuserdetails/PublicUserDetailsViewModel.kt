package io.github.frankolt.githubexplorer.ui.publicuserdetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import io.github.frankolt.githubexplorer.domain.github.interactors.UserInteractor

class PublicUserDetailsViewModel @ViewModelInject constructor(
    private val userInteractor: UserInteractor
) : ViewModel() {
}
