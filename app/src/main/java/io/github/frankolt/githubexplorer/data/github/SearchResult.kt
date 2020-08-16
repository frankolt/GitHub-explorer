package io.github.frankolt.githubexplorer.data.github

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResult(
    @Json(name = "total_count") val totalCount: Int,
    @Json(name = "incomplete_results") val areResultsIncomplete: Boolean,
    @Json(name = "items") val items: List<SearchResultItem>
)
