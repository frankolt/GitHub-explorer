package io.github.frankolt.githubexplorer.domain.github.mappers.repository

import io.github.frankolt.githubexplorer.data.http.github.models.RepositoryResponse
import io.github.frankolt.githubexplorer.domain.github.mappers.license.LicenseMapper
import io.github.frankolt.githubexplorer.domain.github.mappers.repositorypermissions.RepositoryPermissionsMapper
import io.github.frankolt.githubexplorer.domain.github.mappers.user.UserMapper
import io.github.frankolt.githubexplorer.domain.github.models.Repository
import javax.inject.Inject

class RepositoryMapperImpl @Inject constructor(
    private val userMapper: UserMapper,
    private val repositoryPermissionsMapper: RepositoryPermissionsMapper,
    private val licenseMapper: LicenseMapper
) : RepositoryMapper {

    /**
     * Maps the `RepositoryResponse` model to the `Repository` model. If mandatory fields are
     * missing, `NullPointerException` is thrown.
     */
    override fun map(inputModel: RepositoryResponse): Repository = Repository(
        inputModel.id,
        inputModel.nodeId,
        inputModel.name!!,
        inputModel.fullName!!,
        userMapper.map(inputModel.owner!!),
        inputModel.isPrivate,
        inputModel.htmlUrl,
        inputModel.description,
        inputModel.isFork,
        inputModel.url,
        inputModel.archiveUrl,
        inputModel.assigneesUrl,
        inputModel.blobsUrl,
        inputModel.branchesUrl,
        inputModel.collaboratorsUrl,
        inputModel.commentsUrl,
        inputModel.compareUrl,
        inputModel.contentsUrl,
        inputModel.contributorsUrl,
        inputModel.deploymentsUrl,
        inputModel.downloadsUrl,
        inputModel.eventsUrl,
        inputModel.forksUrl,
        inputModel.gitCommitsUrl,
        inputModel.gitRefsUrl,
        inputModel.gitTagsUrl,
        inputModel.gitUrl,
        inputModel.issueCommentUrl,
        inputModel.issueEventsUrl,
        inputModel.issuesUrl,
        inputModel.keysUrl,
        inputModel.labelsUrl,
        inputModel.languagesUrl,
        inputModel.mergesUrl,
        inputModel.milestonesUrl,
        inputModel.pullsUrl,
        inputModel.releasesUrl,
        inputModel.sshUrl,
        inputModel.stargazersUrl,
        inputModel.statusesUrl,
        inputModel.subscribersUrl,
        inputModel.subscriptionUrl,
        inputModel.tagsUrl,
        inputModel.teamsUrl,
        inputModel.treesUrl,
        inputModel.cloneUrl,
        inputModel.mirrorUrl,
        inputModel.hooksUrl,
        inputModel.svnUrl,
        inputModel.createdAt,
        inputModel.updatedAt,
        inputModel.pushedAt,
        inputModel.homepage,
        inputModel.size,
        inputModel.stargazersCount,
        inputModel.watchersCount,
        inputModel.language,
        inputModel.forksCount,
        inputModel.openIssuesCount,
        inputModel.masterBranch,
        inputModel.defaultBranch,
        inputModel.score,
        inputModel.isTemplate,
        inputModel.topics,
        inputModel.hasIssues,
        inputModel.hasProjects,
        inputModel.hasWiki,
        inputModel.hasPages,
        inputModel.hasDownloads,
        inputModel.isArchived,
        inputModel.isDisabled,
        inputModel.visibility,
        inputModel.permissions?.let { repositoryPermissionsMapper.map(it) },
        inputModel.allowRebaseMerge,
        inputModel.templateRepository?.let { map(it) },
        inputModel.tmpCloneToken,
        inputModel.isSquashMergeAllowed,
        inputModel.shouldDeleteBranchOnMerge,
        inputModel.subscribersCount,
        inputModel.networkCount,
        inputModel.license?.let { licenseMapper.map(it) },
        inputModel.organization?.let { userMapper.map(it) }
    )
}
