package io.github.frankolt.githubexplorer.ui.extensions

import androidx.recyclerview.widget.RecyclerView
import io.github.frankolt.githubexplorer.ui.util.DebouncedOnScrollListener

fun RecyclerView.addDebouncedOnScrollListener(
    delayMs: Long,
    listener: RecyclerView.OnScrollListener
) {
    addOnScrollListener(DebouncedOnScrollListener(delayMs, listener))
}
