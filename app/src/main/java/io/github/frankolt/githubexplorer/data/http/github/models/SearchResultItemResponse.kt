package io.github.frankolt.githubexplorer.data.http.github.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResultItemResponse(
    @Json(name = "id") val id: Long? = null,
    @Json(name = "node_id") val nodeId: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "full_name") val fullName: String? = null,
    @Json(name = "owner") val owner: OwnerResponse? = null,
    @Json(name = "private") val isPrivate: Boolean? = null,
    @Json(name = "html_url") val htmlUrl: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "fork") val isFork: Boolean? = null,
    @Json(name = "url") val url: String? = null,
    @Json(name = "created_at") val createdAt: String? = null,
    @Json(name = "updated_at") val updatedAt: String? = null,
    @Json(name = "pushed_at") val pushedAt: String? = null,
    @Json(name = "homepage") val homepage: String? = null,
    @Json(name = "size") val size: Long? = null,
    @Json(name = "stargazers_count") val stargazersCount: Long? = null,
    @Json(name = "watchers_count") val watchersCount: Long? = null,
    @Json(name = "language") val language: String? = null,
    @Json(name = "forks_count") val forksCount: Long? = null,
    @Json(name = "open_issues_count") val openIssuesCount: Long? = null,
    @Json(name = "master_branch") val masterBranch: String? = null,
    @Json(name = "default_branch") val defaultBranch: String? = null,
    @Json(name = "score") val score: Long? = null
)
