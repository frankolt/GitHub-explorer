package io.github.frankolt.githubexplorer.data.http.github

import io.github.frankolt.githubexplorer.data.http.github.models.RepositoryResponse
import io.github.frankolt.githubexplorer.data.http.github.models.UserResponse
import io.github.frankolt.githubexplorer.data.http.github.models.RepositorySearchResultResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubService {

    @GET("/search/repositories")
    suspend fun searchRepositories(
        @Query("q") q: String,
        @Query("sort") sort: String? = null,
        @Query("order") order: String? = null,
        @Query("per_page") perPage: Int? = null,
        @Query("page") page: Int? = null
    ): RepositorySearchResultResponse

    @GET("/users/{username}")
    suspend fun getUser(
        @Path("username") username: String
    ): UserResponse

    @GET("/repos/{owner}/{repo}")
    suspend fun getRepository(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): RepositoryResponse
}
