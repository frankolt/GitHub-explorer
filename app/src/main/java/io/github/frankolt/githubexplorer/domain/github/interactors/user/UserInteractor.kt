package io.github.frankolt.githubexplorer.domain.github.interactors.user

import io.github.frankolt.githubexplorer.data.http.github.GitHubService
import io.github.frankolt.githubexplorer.domain.github.interactors.AsyncResult
import io.github.frankolt.githubexplorer.domain.github.mappers.UserMapper
import io.github.frankolt.githubexplorer.domain.github.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class UserInteractor @Inject constructor(
    private val gitHubService: GitHubService
) {

    suspend fun load(username: String): AsyncResult<User> = try {
        AsyncResult.Success(UserMapper.fromResponse(gitHubService.getUser(username)))
    } catch (e: Exception) {
        AsyncResult.Failure(e)
    }
}
