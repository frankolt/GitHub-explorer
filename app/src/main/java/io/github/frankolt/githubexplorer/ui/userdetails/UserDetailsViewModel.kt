package io.github.frankolt.githubexplorer.ui.userdetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.frankolt.githubexplorer.domain.github.interactors.UserInteractor
import io.github.frankolt.githubexplorer.ui.userdetails.state.UserDetails
import kotlinx.coroutines.launch

class UserDetailsViewModel @ViewModelInject constructor(
    private val userInteractor: UserInteractor
) : ViewModel() {

    private val _userDetails = MutableLiveData<UserDetails>()

    val userDetails: LiveData<UserDetails>
        get() = _userDetails

    fun getUserDetails(username: String) = viewModelScope.launch {
        _userDetails.value = userInteractor.execute(username).let {
            val followers = it.followers ?: 0
            val following = it.following ?: 0
            UserDetails(
                it.avatarUrl,
                it.login,
                it.name,
                Pair(followers, following),
                it.company,
                it.location,
                it.email,
                it.blog
            )
        }
    }
}
