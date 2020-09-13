package io.github.frankolt.githubexplorer.domain.github.mappers.user

import io.github.frankolt.githubexplorer.data.http.github.models.UserResponse
import io.github.frankolt.githubexplorer.domain.github.mappers.userplan.UserPlanMapper
import io.github.frankolt.githubexplorer.domain.github.models.User
import javax.inject.Inject

class UserMapperImpl @Inject constructor(
    private val userPlanMapper: UserPlanMapper
) : UserMapper {

    /**
     * Maps the `UserResponse` model to the `User` model. If mandatory fields are missing,
     * `NullPointerException` is thrown.
     */
    override fun map(inputModel: UserResponse) = User(
        inputModel.login!!,
        inputModel.id,
        inputModel.nodeId,
        inputModel.avatarUrl,
        inputModel.gravatarId,
        inputModel.url,
        inputModel.htmlUrl,
        inputModel.followersUrl,
        inputModel.followingUrl,
        inputModel.gistsUrl,
        inputModel.starredUrl,
        inputModel.subscriptionsUrl,
        inputModel.organizationsUrl,
        inputModel.reposUrl,
        inputModel.eventsUrl,
        inputModel.receivedEventsUrl,
        inputModel.type,
        inputModel.isSiteAdmin,
        inputModel.name,
        inputModel.company,
        inputModel.blog,
        inputModel.location,
        inputModel.email,
        inputModel.isHireable,
        inputModel.bio,
        inputModel.twitterUsername,
        inputModel.publicRepos,
        inputModel.publicGists,
        inputModel.followers,
        inputModel.following,
        inputModel.createdAt,
        inputModel.updatedAt,
        inputModel.privateGists,
        inputModel.totalPrivateRepos,
        inputModel.ownedPrivateRepos,
        inputModel.diskUsage,
        inputModel.collaborators,
        inputModel.hasTwoFactorAuthentication,
        inputModel.plan?.let { userPlanMapper.map(it) }
    )
}
