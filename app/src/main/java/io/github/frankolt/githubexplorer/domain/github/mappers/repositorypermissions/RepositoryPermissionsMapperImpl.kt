package io.github.frankolt.githubexplorer.domain.github.mappers.repositorypermissions

import io.github.frankolt.githubexplorer.data.http.github.models.RepositoryPermissionsResponse
import io.github.frankolt.githubexplorer.domain.github.models.RepositoryPermissions
import javax.inject.Inject

class RepositoryPermissionsMapperImpl @Inject constructor(
) : RepositoryPermissionsMapper {

    override fun map(inputModel: RepositoryPermissionsResponse) = RepositoryPermissions(
        inputModel.admin,
        inputModel.push,
        inputModel.pull,
        inputModel.maintain,
        inputModel.triage
    )
}