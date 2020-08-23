package io.github.frankolt.githubexplorer.ui.repositorysearch

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.frankolt.githubexplorer.domain.github.interactors.AsyncResult
import io.github.frankolt.githubexplorer.domain.github.interactors.repositorysearch.LastPageReachedException
import io.github.frankolt.githubexplorer.domain.github.interactors.repositorysearch.RepositorySearchInteractor
import io.github.frankolt.githubexplorer.domain.github.interactors.repositorysearch.RequestInProgressException
import io.github.frankolt.githubexplorer.domain.github.models.Repository
import io.github.frankolt.githubexplorer.ui.GENERIC_ERROR
import io.github.frankolt.githubexplorer.ui.arch.SingleLiveEvent
import io.github.frankolt.githubexplorer.ui.repositorysearch.events.RepositorySearchEvent
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Error
import java.lang.Exception

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
        if (query != _query.value || _searchResultItems.value.isNullOrEmpty()) {
            _query.value = query
            try {
                val result = repositorySearchInteractor.load(query)
                if (result is AsyncResult.Success) {
                    _searchResultItems.value = result.value.items
                } else {
                    events.value = RepositorySearchEvent.Error(GENERIC_ERROR)
                }
            } catch (e: RequestInProgressException) {
                // Do nothing.
                Timber.e(e)
            }
        }
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

    private fun loadNextPage() = viewModelScope.launch {
        val oldItems = _searchResultItems.value ?: throw IllegalStateException("No old items.")
        try {
            val result = repositorySearchInteractor.loadNextPage()
            if (result is AsyncResult.Success) {
                // Won't be `null` or empty. This is checked by the interactor.
                val newItems = result.value.items!!
                _searchResultItems.value = oldItems + newItems
            } else {
                events.value = RepositorySearchEvent.Error(GENERIC_ERROR)
            }
        } catch (e: RequestInProgressException) {
            // Do nothing.
            Timber.e(e)
        } catch (e: LastPageReachedException) {
            // Do nothing.
            Timber.e(e)
        }
    }
}
