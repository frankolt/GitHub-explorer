package io.github.frankolt.githubexplorer.ui.repositorysearch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.frankolt.githubexplorer.domain.github.interactors.AsyncResult
import io.github.frankolt.githubexplorer.domain.github.interactors.repositorysearch.LastPageReachedException
import io.github.frankolt.githubexplorer.domain.github.interactors.repositorysearch.RepositorySearchInteractor
import io.github.frankolt.githubexplorer.domain.github.interactors.repositorysearch.RepositorySearchParameters
import io.github.frankolt.githubexplorer.domain.github.interactors.repositorysearch.RequestInProgressException
import io.github.frankolt.githubexplorer.ui.arch.SingleLiveEvent
import io.github.frankolt.githubexplorer.ui.repositorysearch.events.RepositorySearchEvent
import io.github.frankolt.githubexplorer.ui.repositorysearch.state.PaginationState
import io.github.frankolt.githubexplorer.ui.repositorysearch.state.QueryState
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RepositorySearchViewModel @Inject constructor(
    private val repositorySearchInteractor: RepositorySearchInteractor
) : ViewModel() {

    private val _query = MutableLiveData<String>()

    val query: LiveData<String>
        get() = _query

    private val _queryState = MutableLiveData<QueryState>()

    val queryState: LiveData<QueryState>
        get() = _queryState

    val events = SingleLiveEvent<RepositorySearchEvent>()

    fun search(query: String) {
        if (query == _query.value) {
            return
        }

        _query.value = query

        if (query.isBlank()) {
            _queryState.value = QueryState.Empty
            return
        }

        load(query)
    }

    fun retry() {
        _query.value?.let { load(it) }
    }

    fun openUserDetails(username: String) {
        events.value = RepositorySearchEvent.OpenUserDetails(username)
    }

    fun openRepositoryDetails(owner: String, repo: String) {
        events.value = RepositorySearchEvent.OpenRepositoryDetails(owner, repo)
    }

    fun onListScrolled(visibleItemCount: Int, lastVisibleItemPosition: Int, totalItemCount: Int) {
        if (visibleItemCount + lastVisibleItemPosition + VISIBLE_ITEM_THRESHOLD >= totalItemCount) {
            loadNextPage()
        }
    }

    fun retryNextPage() {
        loadNextPage()
    }

    private fun load(query: String) = viewModelScope.launch {
        try {
            _queryState.value = QueryState.Loading
            val result = repositorySearchInteractor.execute(RepositorySearchParameters(query))
            if (result is AsyncResult.Success) {
                val items = result.value.items
                if (items.isNullOrEmpty()) {
                    _queryState.value = QueryState.Empty
                } else {
                    _queryState.value = QueryState.Loaded(result.value.items)
                }
            } else {
                _queryState.value = QueryState.Error
            }
        } catch (e: RequestInProgressException) {
            // Do nothing.
            Timber.e(e)
        }
    }

    private fun loadNextPage() = viewModelScope.launch {
        val state = if (_queryState.value is QueryState.Loaded) {
            _queryState.value as QueryState.Loaded
        } else {
            throw IllegalStateException("No old items.")
        }

        try {
            _queryState.value = state.copy(paginationState = PaginationState.Loading)
            val result = repositorySearchInteractor.loadNextPage()
            if (result is AsyncResult.Success) {
                // Won't be `null` or empty. This is checked by the interactor.
                val newItems = result.value.items!!
                _queryState.value = QueryState.Loaded(state.items + newItems)
            } else {
                _queryState.value = state.copy(paginationState = PaginationState.Error)
            }
        } catch (e: RequestInProgressException) {
            // Do nothing.
            Timber.e(e)
        } catch (e: LastPageReachedException) {
            Timber.e(e)
            _queryState.value = state.copy(paginationState = PaginationState.None)
        }
    }
}
