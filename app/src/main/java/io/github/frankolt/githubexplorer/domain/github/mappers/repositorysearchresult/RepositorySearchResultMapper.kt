package io.github.frankolt.githubexplorer.domain.github.mappers.repositorysearchresult

import io.github.frankolt.githubexplorer.data.http.github.models.RepositorySearchResultResponse
import io.github.frankolt.githubexplorer.domain.github.mappers.ModelMapper
import io.github.frankolt.githubexplorer.domain.github.models.RepositorySearchResult

interface RepositorySearchResultMapper :
    ModelMapper<RepositorySearchResultResponse, RepositorySearchResult>
