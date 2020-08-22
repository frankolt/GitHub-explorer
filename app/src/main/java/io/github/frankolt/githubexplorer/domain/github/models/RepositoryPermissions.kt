package io.github.frankolt.githubexplorer.domain.github.models

data class RepositoryPermissions(
    val admin: Boolean? = null,
    val push: Boolean? = null,
    val pull: Boolean? = null,
    val maintain: Boolean? = null,
    val triage: Boolean? = null
)
