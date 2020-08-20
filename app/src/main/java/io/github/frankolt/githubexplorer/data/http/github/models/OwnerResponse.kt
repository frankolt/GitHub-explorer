package io.github.frankolt.githubexplorer.data.http.github.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OwnerResponse(
    @Json(name = "login") val login: String? = null,
    @Json(name = "id") val id: Long? = null,
    @Json(name = "node_id") val nodeId: String? = null,
    @Json(name = "avatar_url") val avatarUrl: String? = null,
    @Json(name = "gravatar_url") val gravatarId: String? = null,
    @Json(name = "url") val url: String? = null,
    @Json(name = "received_events_url") val receivedEventsUrl: String? = null,
    @Json(name = "type") val type: String? = null
)
