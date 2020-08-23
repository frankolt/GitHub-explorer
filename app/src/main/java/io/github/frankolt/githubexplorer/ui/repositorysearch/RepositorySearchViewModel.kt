package io.github.frankolt.githubexplorer.ui.repositorysearch

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.frankolt.githubexplorer.domain.github.interactors.RepositorySearchInteractor
import io.github.frankolt.githubexplorer.domain.github.models.Repository
import io.github.frankolt.githubexplorer.ui.arch.SingleLiveEvent
import io.github.frankolt.githubexplorer.ui.repositorysearch.events.RepositorySearchEvent
import kotlinx.coroutines.launch
import timber.log.Timber

class RepositorySearchViewModel @ViewModelInject constructor(
    private val repositorySearchInteractor: RepositorySearchInteractor
) : ViewModel() {

    private val _query = MutableLiveData<String>()

    val query: LiveData<String>
        get() = _query

    private val _searchResultItems = MutableLiveData<List<Repository>>()

    val searchResultItems: LiveData<List<Repository>>
        get() = _searchResultItems

    val events = SingleLiveEvent<RepositorySearchEvent>()

    fun search(query: String) = viewModelScope.launch {
        _query.value = query
        Timber.d("Query: $query")
    }

    // TODO: Remove. For testing purposes only.
    fun searchTimber() = viewModelScope.launch {
        _searchResultItems.value = repositorySearchInteractor.execute("timber").items
    }

    fun openUserDetails(username: String) {
        events.value = RepositorySearchEvent.OpenUserDetails(username)
    }

    fun openRepositoryDetails(owner: String, repo: String) {
        events.value = RepositorySearchEvent.OpenRepositoryDetails(owner, repo)
    }
}
