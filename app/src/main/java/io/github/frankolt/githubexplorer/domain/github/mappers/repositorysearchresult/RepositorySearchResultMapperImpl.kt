package io.github.frankolt.githubexplorer.domain.github.mappers.repositorysearchresult

import io.github.frankolt.githubexplorer.data.http.github.models.RepositorySearchResultResponse
import io.github.frankolt.githubexplorer.domain.github.mappers.repository.RepositoryMapper
import io.github.frankolt.githubexplorer.domain.github.models.RepositorySearchResult
import javax.inject.Inject

class RepositorySearchResultMapperImpl @Inject constructor(
    private val repositoryMapper: RepositoryMapper
) : RepositorySearchResultMapper {

    /**
     * Maps the `RepositorySearchResultResponse` model to the `RepositorySearchResult` model. If
     * mandatory fields are missing from the repository items, they are left out.
     */
    override fun map(inputModel: RepositorySearchResultResponse) = RepositorySearchResult(
        inputModel.totalCount,
        inputModel.areResultsIncomplete,
        inputModel.items?.mapNotNull {
            try {
                repositoryMapper.map(it)
            } catch (e: NullPointerException) {
                null
            }
        }
    )
}
