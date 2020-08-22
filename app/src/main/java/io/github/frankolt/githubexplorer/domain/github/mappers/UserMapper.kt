package io.github.frankolt.githubexplorer.domain.github.mappers

import io.github.frankolt.githubexplorer.data.http.github.models.UserResponse
import io.github.frankolt.githubexplorer.domain.github.models.User

object UserMapper {

    fun fromResponse(response: UserResponse) = User(
        response.login,
        response.id,
        response.nodeId,
        response.avatarUrl,
        response.gravatarId,
        response.url,
        response.htmlUrl,
        response.followersUrl,
        response.followingUrl,
        response.gistsUrl,
        response.starredUrl,
        response.subscriptionsUrl,
        response.organizationsUrl,
        response.reposUrl,
        response.eventsUrl,
        response.receivedEventsUrl,
        response.type,
        response.isSiteAdmin,
        response.name,
        response.company,
        response.blog,
        response.location,
        response.email,
        response.isHireable,
        response.bio,
        response.twitterUsername,
        response.publicRepos,
        response.publicGists,
        response.followers,
        response.following,
        response.createdAt,
        response.updatedAt,
        response.privateGists,
        response.totalPrivateRepos,
        response.ownedPrivateRepos,
        response.diskUsage,
        response.collaborators,
        response.hasTwoFactorAuthentication,
        response.plan?.let { UserPlanMapper.fromResponse(it) }
    )
}
