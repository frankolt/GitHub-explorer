package io.github.frankolt.githubexplorer.domain.github.mappers

import io.github.frankolt.githubexplorer.data.http.github.models.RepositoryPermissionsResponse
import io.github.frankolt.githubexplorer.domain.github.models.RepositoryPermissions

object RepositoryPermissionsMapper {

    fun fromResponse(response: RepositoryPermissionsResponse) = RepositoryPermissions(
        response.admin,
        response.push,
        response.pull,
        response.maintain,
        response.triage
    )
}
