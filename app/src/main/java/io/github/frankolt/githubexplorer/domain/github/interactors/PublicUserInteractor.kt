package io.github.frankolt.githubexplorer.domain.github.interactors

import io.github.frankolt.githubexplorer.data.http.github.GitHubService
import io.github.frankolt.githubexplorer.domain.github.mappers.UserMapper
import io.github.frankolt.githubexplorer.domain.github.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PublicUserInteractor @Inject constructor(
    private val gitHubService: GitHubService
) {

    suspend fun execute(username: String): User = withContext(Dispatchers.IO) {
        UserMapper.fromResponse(gitHubService.getUser(username))
    }
}
