package io.github.frankolt.githubexplorer.domain.github.interactors.repository

import io.github.frankolt.githubexplorer.data.http.github.GitHubService
import io.github.frankolt.githubexplorer.domain.github.interactors.AsyncResult
import io.github.frankolt.githubexplorer.domain.github.mappers.repository.RepositoryMapper
import io.github.frankolt.githubexplorer.domain.github.models.Repository
import javax.inject.Inject

class RepositoryInteractorImpl @Inject constructor(
    private val gitHubService: GitHubService,
    private val repositoryMapper: RepositoryMapper
) : RepositoryInteractor {

    override suspend fun execute(parameters: RepositoryParameters): AsyncResult<Repository> = try {
        AsyncResult.Success(
            repositoryMapper.map(gitHubService.getRepository(parameters.owner, parameters.repo))
        )
    } catch (e: Exception) {
        AsyncResult.Failure(e)
    }
}
