package io.github.frankolt.githubexplorer.data.github

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResultItem(
    @Json(name = "id") val id: Int? = null,
    @Json(name = "node_id") val nodeId: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "full_name") val fullName: String? = null,
    @Json(name = "owner") val owner: Owner? = null,
    @Json(name = "private") val isPrivate: Boolean? = null,
    @Json(name = "html_url") val htmlUrl: String? = null,
    @Json(name = "description") val description: String? = null,
    @Json(name = "fork") val isFork: Boolean? = null,
    @Json(name = "url") val url: String? = null,
    @Json(name = "created_at") val createdAt: String? = null,
    @Json(name = "updated_at") val updatedAt: String? = null,
    @Json(name = "pushed_at") val pushedAt: String? = null,
    @Json(name = "homepage") val homepage: String? = null,
    @Json(name = "size") val size: Int? = null,
    @Json(name = "stargazers_count") val stargazersCount: Int? = null,
    @Json(name = "watchers_count") val watchersCount: Int? = null,
    @Json(name = "language") val language: String? = null,
    @Json(name = "forks_count") val forksCount: Int? = null,
    @Json(name = "open_issues_count") val openIssuesCount: Int? = null,
    @Json(name = "master_branch") val masterBranch: String? = null,
    @Json(name = "default_branch") val defaultBranch: String? = null,
    @Json(name = "score") val score: Int? = null
)
