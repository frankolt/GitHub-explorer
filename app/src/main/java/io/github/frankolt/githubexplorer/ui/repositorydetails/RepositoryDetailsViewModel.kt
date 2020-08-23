package io.github.frankolt.githubexplorer.ui.repositorydetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.frankolt.githubexplorer.domain.github.interactors.AsyncResult
import io.github.frankolt.githubexplorer.domain.github.interactors.repository.RepositoryInteractor
import io.github.frankolt.githubexplorer.domain.github.models.Repository
import io.github.frankolt.githubexplorer.ui.GENERIC_ERROR
import io.github.frankolt.githubexplorer.ui.arch.SingleLiveEvent
import io.github.frankolt.githubexplorer.ui.repositorydetails.events.RepositoryDetailsEvent
import kotlinx.coroutines.launch

class RepositoryDetailsViewModel @ViewModelInject constructor(
    private val repositoryInteractor: RepositoryInteractor
) : ViewModel() {

    private val _repository = MutableLiveData<Repository>()

    val repository: LiveData<Repository>
        get() = _repository

    val events = SingleLiveEvent<RepositoryDetailsEvent>()

    fun getRepositoryDetails(owner: String, repo: String) = viewModelScope.launch {
        val result = repositoryInteractor.load(owner, repo)
        if (result is AsyncResult.Success) {
            _repository.value = result.value
        } else {
            events.value = RepositoryDetailsEvent.Error(GENERIC_ERROR)
        }
    }

    fun openInBrowser() {
        // TODO: What if it's `null`?
        _repository.value?.htmlUrl?.let { events.value = RepositoryDetailsEvent.OpenInBrowser(it) }
    }

    fun openUserDetails() {
        // TODO: What if it's `null`?
        _repository.value?.owner?.login?.let {
            events.value = RepositoryDetailsEvent.OpenUserDetails(it)
        }
    }
}
