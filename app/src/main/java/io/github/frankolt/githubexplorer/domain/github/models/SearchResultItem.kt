package io.github.frankolt.githubexplorer.domain.github.models

data class SearchResultItem(
    val id: Long? = null,
    val nodeId: String? = null,
    val name: String? = null,
    val fullName: String? = null,
    val owner: Owner? = null,
    val isPrivate: Boolean? = null,
    val htmlUrl: String? = null,
    val description: String? = null,
    val isFork: Boolean? = null,
    val url: String? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val pushedAt: String? = null,
    val homepage: String? = null,
    val size: Long? = null,
    val stargazersCount: Long? = null,
    val watchersCount: Long? = null,
    val language: String? = null,
    val forksCount: Long? = null,
    val openIssuesCount: Long? = null,
    val masterBranch: String? = null,
    val defaultBranch: String? = null,
    val score: Long? = null
)
