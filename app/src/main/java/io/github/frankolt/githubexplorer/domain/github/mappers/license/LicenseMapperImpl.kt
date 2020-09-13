package io.github.frankolt.githubexplorer.domain.github.mappers.license

import io.github.frankolt.githubexplorer.data.http.github.models.LicenseResponse
import io.github.frankolt.githubexplorer.domain.github.models.License
import javax.inject.Inject

class LicenseMapperImpl @Inject constructor(
) : LicenseMapper {

    override fun map(inputModel: LicenseResponse) = License(
        inputModel.key,
        inputModel.name,
        inputModel.spdxId,
        inputModel.url,
        inputModel.nodeId
    )
}
