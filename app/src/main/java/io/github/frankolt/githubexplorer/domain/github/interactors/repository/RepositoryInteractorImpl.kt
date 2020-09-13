package io.github.frankolt.githubexplorer.domain.github.interactors.repository

import io.github.frankolt.githubexplorer.data.http.github.GitHubService
import io.github.frankolt.githubexplorer.domain.github.interactors.AsyncResult
import io.github.frankolt.githubexplorer.domain.github.mappers.repository.RepositoryMapper
import io.github.frankolt.githubexplorer.domain.github.models.Repository
import javax.inject.Inject

class RepositoryInteractorImpl @Inject constructor(
    private val gitHubService: GitHubService,
    private val repositoryMapper: RepositoryMapper
) {

    suspend fun load(owner: String, repo: String): AsyncResult<Repository> = try {
        AsyncResult.Success(repositoryMapper.map(gitHubService.getRepository(owner, repo)))
    } catch (e: Exception) {
        AsyncResult.Failure(e)
    }
}
