package io.github.frankolt.githubexplorer.ui.repositorydetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.frankolt.githubexplorer.domain.github.interactors.AsyncResult
import io.github.frankolt.githubexplorer.domain.github.interactors.repository.RepositoryInteractor
import io.github.frankolt.githubexplorer.domain.github.interactors.repository.RepositoryParameters
import io.github.frankolt.githubexplorer.ui.arch.SingleLiveEvent
import io.github.frankolt.githubexplorer.ui.repositorydetails.events.RepositoryDetailsEvent
import io.github.frankolt.githubexplorer.ui.repositorydetails.state.RepositoryDetailsState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailsViewModel @Inject constructor(
    private val repositoryInteractor: RepositoryInteractor
) : ViewModel() {

    private val _repositoryDetailsState = MutableLiveData<RepositoryDetailsState>()

    val repositoryDetailsState: LiveData<RepositoryDetailsState>
        get() = _repositoryDetailsState

    val events = SingleLiveEvent<RepositoryDetailsEvent>()

    fun getRepositoryDetails(owner: String, repo: String) = viewModelScope.launch {
        if (_repositoryDetailsState.value is RepositoryDetailsState.Loading || _repositoryDetailsState.value is RepositoryDetailsState.Loaded) {
            return@launch
        }
        _repositoryDetailsState.value = RepositoryDetailsState.Loading
        val result = repositoryInteractor.execute(RepositoryParameters(owner, repo))
        if (result is AsyncResult.Success) {
            _repositoryDetailsState.value = RepositoryDetailsState.Loaded(result.value)
        } else {
            _repositoryDetailsState.value = RepositoryDetailsState.Error
        }
    }

    fun openInBrowser() {
        _repositoryDetailsState.value?.let { state ->
            if (state is RepositoryDetailsState.Loaded) {
                state.repository.htmlUrl?.let {
                    events.value = RepositoryDetailsEvent.OpenInBrowser(it)
                }
            }
        }
    }

    fun openUserDetails() {
        _repositoryDetailsState.value?.let { state ->
            if (state is RepositoryDetailsState.Loaded) {
                state.repository.owner.login.let {
                    events.value = RepositoryDetailsEvent.OpenUserDetails(it)
                }
            }
        }
    }
}
