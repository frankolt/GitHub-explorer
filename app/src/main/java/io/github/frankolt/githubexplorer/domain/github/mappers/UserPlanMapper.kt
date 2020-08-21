package io.github.frankolt.githubexplorer.domain.github.mappers

import io.github.frankolt.githubexplorer.data.http.github.models.UserPlanResponse
import io.github.frankolt.githubexplorer.domain.github.models.UserPlan

object UserPlanMapper {

    fun fromResponse(response: UserPlanResponse) = UserPlan(
        response.name,
        response.space,
        response.collaborators,
        response.privateRepos
    )
}
