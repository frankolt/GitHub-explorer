package io.github.frankolt.githubexplorer.domain.github.mappers

import io.github.frankolt.githubexplorer.data.http.github.models.PublicUserResponse
import io.github.frankolt.githubexplorer.domain.github.models.PublicUser

object PublicUserMapper {
    
    fun fromResponse(response: PublicUserResponse) = PublicUser(
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
        response.siteAdmin,
        response.name,
        response.company,
        response.blog,
        response.location,
        response.email,
        response.hireable
    )
}
