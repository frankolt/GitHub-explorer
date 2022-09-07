package io.github.frankolt.githubexplorer.domain.github.mappers.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.frankolt.githubexplorer.domain.github.mappers.license.LicenseMapper
import io.github.frankolt.githubexplorer.domain.github.mappers.license.LicenseMapperImpl
import io.github.frankolt.githubexplorer.domain.github.mappers.repository.RepositoryMapper
import io.github.frankolt.githubexplorer.domain.github.mappers.repository.RepositoryMapperImpl
import io.github.frankolt.githubexplorer.domain.github.mappers.repositorypermissions.RepositoryPermissionsMapper
import io.github.frankolt.githubexplorer.domain.github.mappers.repositorypermissions.RepositoryPermissionsMapperImpl
import io.github.frankolt.githubexplorer.domain.github.mappers.repositorysearchresult.RepositorySearchResultMapper
import io.github.frankolt.githubexplorer.domain.github.mappers.repositorysearchresult.RepositorySearchResultMapperImpl
import io.github.frankolt.githubexplorer.domain.github.mappers.user.UserMapper
import io.github.frankolt.githubexplorer.domain.github.mappers.user.UserMapperImpl
import io.github.frankolt.githubexplorer.domain.github.mappers.userplan.UserPlanMapper
import io.github.frankolt.githubexplorer.domain.github.mappers.userplan.UserPlanMapperImpl

@Module
@InstallIn(SingletonComponent::class)
interface ModelMapperModule {

    @Binds
    fun bindLicenseMapper(mapper: LicenseMapperImpl): LicenseMapper

    @Binds
    fun bindRepositoryPermissionsMapper(mapper: RepositoryPermissionsMapperImpl): RepositoryPermissionsMapper

    @Binds
    fun bindUserPlanMapper(mapper: UserPlanMapperImpl): UserPlanMapper

    @Binds
    fun bindUserMapper(mapper: UserMapperImpl): UserMapper

    @Binds
    fun bindRepositoryMapper(mapper: RepositoryMapperImpl): RepositoryMapper

    @Binds
    fun bindRepositorySearchResultMapper(mapper: RepositorySearchResultMapperImpl): RepositorySearchResultMapper
}
