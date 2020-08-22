package io.github.frankolt.githubexplorer.data.http.github.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LicenseResponse(
    @Json(name = "key") val key: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "spdx_id") val spdxId: String? = null,
    @Json(name = "url") val url: String? = null,
    @Json(name = "node_id") val nodeId: String? = null
)
