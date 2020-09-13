package io.github.frankolt.githubexplorer.domain.github.interactors.user

import io.github.frankolt.githubexplorer.data.http.github.GitHubService
import io.github.frankolt.githubexplorer.domain.github.interactors.AsyncResult
import io.github.frankolt.githubexplorer.domain.github.mappers.user.UserMapper
import io.github.frankolt.githubexplorer.domain.github.models.User
import javax.inject.Inject

class UserInteractorImpl @Inject constructor(
    private val gitHubService: GitHubService,
    private val userMapper: UserMapper
) : UserInteractor {

    override suspend fun execute(parameters: UserParameters): AsyncResult<User> = try {
        AsyncResult.Success(userMapper.map(gitHubService.getUser(parameters.username)))
    } catch (e: Exception) {
        AsyncResult.Failure(e)
    }
}
