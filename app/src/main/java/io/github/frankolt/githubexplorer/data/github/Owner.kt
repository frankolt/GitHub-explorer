package io.github.frankolt.githubexplorer.data.github

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Owner(
    @Json(name = "login") val login: String,
    @Json(name = "id") val id: Int,
    @Json(name = "node_id") val nodeId: String,
    @Json(name = "avatar_url") val avatarUrl: String,
    @Json(name = "gravatar_url") val gravatarId: String,
    @Json(name = "url") val url: String,
    @Json(name = "received_events_url") val receivedEventsUrl: String,
    @Json(name = "type") val type: String
)
