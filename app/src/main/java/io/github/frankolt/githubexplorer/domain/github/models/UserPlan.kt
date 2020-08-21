package io.github.frankolt.githubexplorer.domain.github.models

data class UserPlan(
    val name: String? = null,
    val space: Long? = null,
    val collaborators: Long? = null,
    val privateRepos: Long? = null
)
