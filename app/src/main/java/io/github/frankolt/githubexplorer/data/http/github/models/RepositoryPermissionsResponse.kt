package io.github.frankolt.githubexplorer.data.http.github.models

import com.squareup.moshi.Json

data class RepositoryPermissionsResponse(
    @Json(name = "admin") val admin: Boolean? = null,
    @Json(name = "push") val push: Boolean? = null,
    @Json(name = "pull") val pull: Boolean? = null,
    @Json(name = "maintain") val maintain: Boolean? = null,
    @Json(name = "triage") val triage: Boolean? = null
)
