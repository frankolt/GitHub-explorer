package io.github.frankolt.githubexplorer.domain.github.mappers.repository

import io.github.frankolt.githubexplorer.data.http.github.models.RepositoryResponse
import io.github.frankolt.githubexplorer.domain.github.mappers.ModelMapper
import io.github.frankolt.githubexplorer.domain.github.models.Repository

interface RepositoryMapper : ModelMapper<RepositoryResponse, Repository>
