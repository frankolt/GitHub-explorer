package io.github.frankolt.githubexplorer.domain.github.interactors

sealed class AsyncResult<out T> {

    class Success<T>(val value: T) : AsyncResult<T>()

    class Failure(val throwable: Throwable): AsyncResult<Nothing>()
}
