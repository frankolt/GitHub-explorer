package io.github.frankolt.githubexplorer.domain.github.models

data class PublicUser(
    val login: String? = null,
    val id: Long? = null,
    val nodeId: String? = null,
    val avatarUrl: String? = null,
    val gravatarId: String? = null,
    val url: String? = null,
    val htmlUrl: String? = null,
    val followersUrl: String? = null,
    val followingUrl: String? = null,
    val gistsUrl: String? = null,
    val starredUrl: String? = null,
    val subscriptionsUrl: String? = null,
    val organizationsUrl: String? = null,
    val reposUrl: String? = null,
    val eventsUrl: String? = null,
    val receivedEventsUrl: String? = null,
    val type: String? = null,
    val siteAdmin: Boolean? = null,
    val name: String? = null,
    val company: String? = null,
    val blog: String? = null,
    val location: String? = null,
    val email: String? = null,
    val hireable: Boolean? = null
)
