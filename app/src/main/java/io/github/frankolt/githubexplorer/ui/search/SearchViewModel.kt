package io.github.frankolt.githubexplorer.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.frankolt.githubexplorer.data.sources.http.github.models.SearchResultItemResponse
import io.github.frankolt.githubexplorer.data.sources.http.github.interactors.SearchInteractor
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
    private val searchInteractor: SearchInteractor
) : ViewModel() {

    private var _query = MutableLiveData<String>()

    val query: LiveData<String>
        get() = _query

    private var _searchResultItems = MutableLiveData<List<SearchResultItemResponse>>()

    val searchResultItems: LiveData<List<SearchResultItemResponse>>
        get() = _searchResultItems

    fun search(query: String) = viewModelScope.launch {
        _query.value = query
    }

    // TODO: Remove. For testing purposes only.
    fun searchTimber() = viewModelScope.launch {
        _searchResultItems.value = searchInteractor.execute("timber").items
    }
}
