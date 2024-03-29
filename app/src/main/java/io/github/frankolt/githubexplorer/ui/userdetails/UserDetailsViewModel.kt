package io.github.frankolt.githubexplorer.ui.userdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.frankolt.githubexplorer.domain.github.interactors.AsyncResult
import io.github.frankolt.githubexplorer.domain.github.interactors.user.UserInteractor
import io.github.frankolt.githubexplorer.domain.github.interactors.user.UserParameters
import io.github.frankolt.githubexplorer.ui.arch.SingleLiveEvent
import io.github.frankolt.githubexplorer.ui.userdetails.events.UserDetailsEvent
import io.github.frankolt.githubexplorer.ui.userdetails.state.UserDetailsState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val userInteractor: UserInteractor
) : ViewModel() {

    private val _userDetailsState = MutableLiveData<UserDetailsState>()

    val userDetailsState: LiveData<UserDetailsState>
        get() = _userDetailsState

    val events = SingleLiveEvent<UserDetailsEvent>()

    fun getUserDetails(username: String) = viewModelScope.launch {
        if (_userDetailsState.value is UserDetailsState.Loading || _userDetailsState.value is UserDetailsState.Loaded) {
            return@launch
        }
        _userDetailsState.value = UserDetailsState.Loading
        val result = userInteractor.execute(UserParameters(username))
        if (result is AsyncResult.Success) {
            _userDetailsState.value = UserDetailsState.Loaded(result.value)
        } else {
            _userDetailsState.value = UserDetailsState.Error
        }
    }

    fun openInBrowser() {
        _userDetailsState.value?.let { state ->
            if (state is UserDetailsState.Loaded) {
                state.user.htmlUrl?.let { events.value = UserDetailsEvent.OpenInBrowser(it) }
            }
        }
    }
}
