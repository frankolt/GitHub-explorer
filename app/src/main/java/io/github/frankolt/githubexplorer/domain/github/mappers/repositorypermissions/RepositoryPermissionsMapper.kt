package io.github.frankolt.githubexplorer.domain.github.mappers.repositorypermissions

import io.github.frankolt.githubexplorer.data.http.github.models.RepositoryPermissionsResponse
import io.github.frankolt.githubexplorer.domain.github.mappers.ModelMapper
import io.github.frankolt.githubexplorer.domain.github.models.RepositoryPermissions

interface RepositoryPermissionsMapper :
    ModelMapper<RepositoryPermissionsResponse, RepositoryPermissions>
