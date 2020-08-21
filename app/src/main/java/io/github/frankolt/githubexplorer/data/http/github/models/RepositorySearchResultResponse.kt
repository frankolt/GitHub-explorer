package io.github.frankolt.githubexplorer.data.http.github.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepositorySearchResultResponse(
    @Json(name = "total_count") val totalCount: Long? = null,
    @Json(name = "incomplete_results") val areResultsIncomplete: Boolean? = null,
    @Json(name = "items") val items: List<RepositoryResponse>? = null
)
