package io.github.frankolt.githubexplorer.domain.github.interactors.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.frankolt.githubexplorer.domain.github.interactors.repository.RepositoryInteractor
import io.github.frankolt.githubexplorer.domain.github.interactors.repository.RepositoryInteractorImpl
import io.github.frankolt.githubexplorer.domain.github.interactors.repositorysearch.RepositorySearchInteractor
import io.github.frankolt.githubexplorer.domain.github.interactors.repositorysearch.RepositorySearchInteractorImpl
import io.github.frankolt.githubexplorer.domain.github.interactors.user.UserInteractor
import io.github.frankolt.githubexplorer.domain.github.interactors.user.UserInteractorImpl

@Module
@InstallIn(SingletonComponent::class)
interface InteractorModule {

    @Binds
    fun bindRepositorySearchInteractor(interactor: RepositorySearchInteractorImpl): RepositorySearchInteractor

    @Binds
    fun bindRepositoryInteractor(interactor: RepositoryInteractorImpl): RepositoryInteractor

    @Binds
    fun bindUserInteractor(interactor: UserInteractorImpl): UserInteractor
}
