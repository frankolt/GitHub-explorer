package io.github.frankolt.githubexplorer.ui.search

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SearchViewModel @ViewModelInject constructor(
) : ViewModel() {

    private var _query = MutableLiveData<String>()

    val query: LiveData<String>
        get() = _query

    fun search(query: String) = viewModelScope.launch {
        _query.value = query
    }
}
