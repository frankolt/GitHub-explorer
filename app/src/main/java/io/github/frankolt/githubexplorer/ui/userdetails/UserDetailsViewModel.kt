package io.github.frankolt.githubexplorer.ui.userdetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.frankolt.githubexplorer.domain.github.interactors.AsyncResult
import io.github.frankolt.githubexplorer.domain.github.interactors.user.UserInteractor
import io.github.frankolt.githubexplorer.domain.github.models.User
import io.github.frankolt.githubexplorer.ui.GENERIC_ERROR
import io.github.frankolt.githubexplorer.ui.arch.SingleLiveEvent
import io.github.frankolt.githubexplorer.ui.userdetails.events.UserDetailsEvent
import kotlinx.coroutines.launch

class UserDetailsViewModel @ViewModelInject constructor(
    private val userInteractor: UserInteractor
) : ViewModel() {

    private val _userDetails = MutableLiveData<User>()

    val userDetails: LiveData<User>
        get() = _userDetails

    val events = SingleLiveEvent<UserDetailsEvent>()

    fun getUserDetails(username: String) = viewModelScope.launch {
        val result = userInteractor.load(username)
        if (result is AsyncResult.Success) {
            _userDetails.value = result.value
        } else {
            events.value = UserDetailsEvent.Error(GENERIC_ERROR)
        }
    }

    fun openInBrowser() {
        // TODO: What if it's `null`?
        _userDetails.value?.htmlUrl?.let { events.value = UserDetailsEvent.OpenInBrowser(it) }
    }
}
