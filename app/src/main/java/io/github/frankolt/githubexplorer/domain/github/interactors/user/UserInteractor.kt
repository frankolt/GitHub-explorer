package io.github.frankolt.githubexplorer.domain.github.interactors.user

import io.github.frankolt.githubexplorer.domain.github.interactors.Interactor
import io.github.frankolt.githubexplorer.domain.github.models.User

interface UserInteractor : Interactor<UserParameters, User>
