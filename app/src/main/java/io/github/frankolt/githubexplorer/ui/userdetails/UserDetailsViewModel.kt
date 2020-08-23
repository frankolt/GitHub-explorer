package io.github.frankolt.githubexplorer.ui.userdetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.frankolt.githubexplorer.domain.github.interactors.user.UserInteractor
import io.github.frankolt.githubexplorer.domain.github.models.User
import io.github.frankolt.githubexplorer.ui.arch.SingleLiveEvent
import io.github.frankolt.githubexplorer.ui.userdetails.events.UserDetailsEvent
import io.github.frankolt.githubexplorer.ui.userdetails.state.UserDetails
import kotlinx.coroutines.launch

class UserDetailsViewModel @ViewModelInject constructor(
    private val userInteractor: UserInteractor
) : ViewModel() {

    private val _userDetails = MutableLiveData<UserDetails>()

    val userDetails: LiveData<UserDetails>
        get() = _userDetails

    val events = SingleLiveEvent<UserDetailsEvent>()

    private var user: User? = null

    fun getUserDetails(username: String) = viewModelScope.launch {
        user = userInteractor.execute(username).also {
            val followers = it.followers ?: 0
            val following = it.following ?: 0
            _userDetails.value = UserDetails(
                it.avatarUrl,
                it.login,
                it.name,
                Pair(followers, following),
                it.company,
                it.location,
                it.email,
                it.blog,
                it.bio
            )
        }

    }

    fun openInBrowser() {
        // TODO: What if it's `null`?
        user?.htmlUrl?.let { events.value = UserDetailsEvent.OpenInBrowser(it) }
    }
}
