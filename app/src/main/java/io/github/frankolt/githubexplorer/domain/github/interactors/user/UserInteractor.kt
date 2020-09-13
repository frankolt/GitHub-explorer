package io.github.frankolt.githubexplorer.domain.github.interactors.user

import io.github.frankolt.githubexplorer.data.http.github.GitHubService
import io.github.frankolt.githubexplorer.domain.github.interactors.AsyncResult
import io.github.frankolt.githubexplorer.domain.github.mappers.user.UserMapper
import io.github.frankolt.githubexplorer.domain.github.models.User
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val gitHubService: GitHubService,
    private val userMapper: UserMapper
) {

    suspend fun load(username: String): AsyncResult<User> = try {
        AsyncResult.Success(userMapper.map(gitHubService.getUser(username)))
    } catch (e: Exception) {
        AsyncResult.Failure(e)
    }
}
