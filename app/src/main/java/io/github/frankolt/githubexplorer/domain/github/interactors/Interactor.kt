package io.github.frankolt.githubexplorer.domain.github.interactors

interface Interactor<in Parameters, out Result> {

    suspend fun execute(parameters: Parameters): AsyncResult<Result>
}
