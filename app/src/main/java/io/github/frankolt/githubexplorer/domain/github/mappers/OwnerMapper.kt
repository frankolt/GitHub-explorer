package io.github.frankolt.githubexplorer.domain.github.mappers

import io.github.frankolt.githubexplorer.data.sources.http.github.models.OwnerResponse
import io.github.frankolt.githubexplorer.domain.github.models.Owner

object OwnerMapper {

    fun fromResponse(response: OwnerResponse) = Owner(
        response.login,
        response.id,
        response.nodeId,
        response.avatarUrl,
        response.gravatarId,
        response.url,
        response.receivedEventsUrl,
        response.type
    )
}
