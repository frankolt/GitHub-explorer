package io.github.frankolt.githubexplorer.domain.github.mappers

import io.github.frankolt.githubexplorer.data.http.github.models.LicenseResponse
import io.github.frankolt.githubexplorer.domain.github.models.License

object LicenseMapper {

    fun fromResponse(response: LicenseResponse) = License(
        response.key,
        response.name,
        response.spdxId,
        response.url,
        response.nodeId
    )
}
