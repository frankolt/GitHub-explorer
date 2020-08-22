package io.github.frankolt.githubexplorer.ui.repositorysearch

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.frankolt.githubexplorer.domain.github.interactors.RepositorySearchInteractor
import io.github.frankolt.githubexplorer.domain.github.models.Repository
import kotlinx.coroutines.launch

class RepositorySearchViewModel @ViewModelInject constructor(
    private val repositorySearchInteractor: RepositorySearchInteractor
) : ViewModel() {

    private var _query = MutableLiveData<String>()

    val query: LiveData<String>
        get() = _query

    private var _searchResultItems = MutableLiveData<List<Repository>>()

    val searchResultItems: LiveData<List<Repository>>
        get() = _searchResultItems

    fun search(query: String) = viewModelScope.launch {
        _query.value = query
    }

    // TODO: Remove. For testing purposes only.
    fun searchTimber() = viewModelScope.launch {
        _searchResultItems.value = repositorySearchInteractor.execute("timber").items
    }
}
