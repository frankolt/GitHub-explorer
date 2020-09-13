package io.github.frankolt.githubexplorer.domain.github.interactors.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.github.frankolt.githubexplorer.domain.github.interactors.repository.RepositoryInteractor
import io.github.frankolt.githubexplorer.domain.github.interactors.repository.RepositoryInteractorImpl
import io.github.frankolt.githubexplorer.domain.github.interactors.user.UserInteractor
import io.github.frankolt.githubexplorer.domain.github.interactors.user.UserInteractorImpl

@Module
@InstallIn(ApplicationComponent::class)
interface InteractorModule {

    @Binds
    fun bindRepositoryInteractor(interactor: RepositoryInteractorImpl): RepositoryInteractor

    @Binds
    fun bindUserInteractor(interactor: UserInteractorImpl): UserInteractor
}
