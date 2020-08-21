package io.github.frankolt.githubexplorer.domain.github.mappers

import io.github.frankolt.githubexplorer.data.http.github.models.RepositoryResponse
import io.github.frankolt.githubexplorer.domain.github.models.Repository

object RepositoryMapper {

    fun fromResponse(response: RepositoryResponse) = Repository(
        response.id,
        response.nodeId,
        response.name,
        response.fullName,
        response.owner?.let { UserMapper.fromResponse(it) },
        response.isPrivate,
        response.htmlUrl,
        response.description,
        response.isFork,
        response.url,
        response.createdAt,
        response.updatedAt,
        response.pushedAt,
        response.homepage,
        response.size,
        response.stargazersCount,
        response.watchersCount,
        response.language,
        response.forksCount,
        response.openIssuesCount,
        response.masterBranch,
        response.defaultBranch,
        response.score
    )
}
