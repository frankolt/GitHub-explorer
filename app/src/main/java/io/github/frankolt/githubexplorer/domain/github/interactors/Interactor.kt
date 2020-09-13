package io.github.frankolt.githubexplorer.domain.github.interactors

interface Interactor<in Parameters, out Result> {

    fun execute(parameters: Parameters): Result
}
