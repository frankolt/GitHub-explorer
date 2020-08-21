package io.github.frankolt.githubexplorer.data.http.github.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserPlanResponse(
    @Json(name = "name") val name: String? = null,
    @Json(name = "space") val space: Long? = null,
    @Json(name = "collaborators") val collaborators: Long? = null,
    @Json(name = "private_repos") val privateRepos: Long? = null
)
