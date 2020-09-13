package io.github.frankolt.githubexplorer.domain.github.mappers.user

import io.github.frankolt.githubexplorer.data.http.github.models.UserResponse
import io.github.frankolt.githubexplorer.domain.github.mappers.ModelMapper
import io.github.frankolt.githubexplorer.domain.github.models.User

interface UserMapper : ModelMapper<UserResponse, User>
