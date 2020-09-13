package io.github.frankolt.githubexplorer.domain.github.mappers.userplan

import io.github.frankolt.githubexplorer.data.http.github.models.UserPlanResponse
import io.github.frankolt.githubexplorer.domain.github.models.UserPlan
import javax.inject.Inject

class UserPlanMapperImpl @Inject constructor(
) : UserPlanMapper {

    override fun map(inputModel: UserPlanResponse) = UserPlan(
        inputModel.name,
        inputModel.space,
        inputModel.collaborators,
        inputModel.privateRepos
    )
}
