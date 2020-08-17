package io.github.frankolt.githubexplorer.data.sources.http.github.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResult(
    @Json(name = "total_count") val totalCount: Int? = null,
    @Json(name = "incomplete_results") val areResultsIncomplete: Boolean? = null,
    @Json(name = "items") val items: List<SearchResultItem>? = null
)
