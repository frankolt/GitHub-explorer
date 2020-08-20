package io.github.frankolt.githubexplorer.domain.github.interactors

import io.github.frankolt.githubexplorer.data.http.github.GitHubService
import io.github.frankolt.githubexplorer.domain.github.mappers.PublicUserMapper
import io.github.frankolt.githubexplorer.domain.github.models.PublicUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PublicUserInteractor @Inject constructor(
    private val gitHubService: GitHubService
) {

    suspend fun execute(username: String): PublicUser = withContext(Dispatchers.IO) {
        PublicUserMapper.fromResponse(gitHubService.getPublicUser(username))
    }
}
