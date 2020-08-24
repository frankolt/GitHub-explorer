package io.github.frankolt.githubexplorer.ui.repositorydetails.state

import io.github.frankolt.githubexplorer.domain.github.models.Repository

sealed class RepositoryDetailsState {

    object Loading : RepositoryDetailsState()

    class Loaded(
        val repository: Repository
    ) : RepositoryDetailsState()

    object Error : RepositoryDetailsState()
}
