package io.github.frankolt.githubexplorer.domain.github.models

data class Owner(
    val login: String? = null,
    val id: Long? = null,
    val nodeId: String? = null,
    val avatarUrl: String? = null,
    val gravatarId: String? = null,
    val url: String? = null,
    val receivedEventsUrl: String? = null,
    val type: String? = null
)
