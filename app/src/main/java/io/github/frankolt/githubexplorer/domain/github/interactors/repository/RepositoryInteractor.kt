package io.github.frankolt.githubexplorer.domain.github.interactors.repository

import io.github.frankolt.githubexplorer.data.http.github.GitHubService
import io.github.frankolt.githubexplorer.domain.github.mappers.RepositoryMapper
import io.github.frankolt.githubexplorer.domain.github.models.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryInteractor @Inject constructor(
    private val gitHubService: GitHubService
) {

    suspend fun execute(owner: String, repo: String): Repository {
        return RepositoryMapper.fromResponse(gitHubService.getRepository(owner, repo))
    }
}
