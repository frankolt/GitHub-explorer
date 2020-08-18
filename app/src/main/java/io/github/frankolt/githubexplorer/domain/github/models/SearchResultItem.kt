package io.github.frankolt.githubexplorer.domain.github.models

data class SearchResultItem(
    val id: Int? = null,
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
    val size: Int? = null,
    val stargazersCount: Int? = null,
    val watchersCount: Int? = null,
    val language: String? = null,
    val forksCount: Int? = null,
    val openIssuesCount: Int? = null,
    val masterBranch: String? = null,
    val defaultBranch: String? = null,
    val score: Int? = null
)
