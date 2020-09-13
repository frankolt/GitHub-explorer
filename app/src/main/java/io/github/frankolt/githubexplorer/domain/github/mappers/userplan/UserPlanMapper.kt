package io.github.frankolt.githubexplorer.domain.github.mappers.userplan

import io.github.frankolt.githubexplorer.data.http.github.models.UserPlanResponse
import io.github.frankolt.githubexplorer.domain.github.mappers.ModelMapper
import io.github.frankolt.githubexplorer.domain.github.models.UserPlan

interface UserPlanMapper : ModelMapper<UserPlanResponse, UserPlan>
