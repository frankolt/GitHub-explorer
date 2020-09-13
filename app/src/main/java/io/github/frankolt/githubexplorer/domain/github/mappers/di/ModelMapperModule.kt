package io.github.frankolt.githubexplorer.domain.github.mappers.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.github.frankolt.githubexplorer.domain.github.mappers.license.LicenseMapper
import io.github.frankolt.githubexplorer.domain.github.mappers.license.LicenseMapperImpl
import io.github.frankolt.githubexplorer.domain.github.mappers.repositorypermissions.RepositoryPermissionsMapper
import io.github.frankolt.githubexplorer.domain.github.mappers.repositorypermissions.RepositoryPermissionsMapperImpl

@Module
@InstallIn(ApplicationComponent::class)
interface ModelMapperModule {

    @Binds
    fun bindLicenseMapper(mapper: LicenseMapperImpl): LicenseMapper

    @Binds
    fun bindRepositoryPermissionsMapper(mapper: RepositoryPermissionsMapperImpl): RepositoryPermissionsMapper
}
