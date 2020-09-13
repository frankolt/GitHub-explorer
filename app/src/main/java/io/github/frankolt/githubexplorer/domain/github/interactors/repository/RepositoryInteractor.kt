package io.github.frankolt.githubexplorer.domain.github.interactors.repository

import io.github.frankolt.githubexplorer.domain.github.interactors.Interactor
import io.github.frankolt.githubexplorer.domain.github.models.Repository

interface RepositoryInteractor : Interactor<RepositoryParameters, Repository>
