package io.github.frankolt.githubexplorer.ui.userdetails.state

data class UserDetails(
    val avatarUrl: String? = null,
    val login: String? = null,
    val name: String? = null,
    val followersAndFollowing: Pair<Long, Long> = Pair(0, 0),
    val company: String? = null,
    val location: String? = null,
    val email: String? = null,
    val blog: String? = null
)
