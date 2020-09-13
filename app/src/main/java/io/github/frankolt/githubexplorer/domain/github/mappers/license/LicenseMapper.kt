package io.github.frankolt.githubexplorer.domain.github.mappers.license

import io.github.frankolt.githubexplorer.data.http.github.models.LicenseResponse
import io.github.frankolt.githubexplorer.domain.github.mappers.ModelMapper
import io.github.frankolt.githubexplorer.domain.github.models.License

interface LicenseMapper : ModelMapper<LicenseResponse, License>
