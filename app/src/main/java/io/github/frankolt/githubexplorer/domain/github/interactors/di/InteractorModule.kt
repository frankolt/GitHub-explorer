package io.github.frankolt.githubexplorer.domain.github.interactors.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.github.frankolt.githubexplorer.domain.github.interactors.repository.RepositoryInteractor
import io.github.frankolt.githubexplorer.domain.github.interactors.repository.RepositoryInteractorImpl

@Module
@InstallIn(ApplicationComponent::class)
interface InteractorModule {

    @Binds
    fun bindRepositoryInteractor(interactor: RepositoryInteractorImpl): RepositoryInteractor
}
