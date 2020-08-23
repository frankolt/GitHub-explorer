package io.github.frankolt.githubexplorer.ui.util

import android.os.Handler
import androidx.recyclerview.widget.RecyclerView

class DebouncedOnScrollListener(
    private val delayMs: Long,
    private val listener: RecyclerView.OnScrollListener
) : RecyclerView.OnScrollListener() {

    private val handler = Handler()

    private var runnable: Runnable? = null

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        runnable?.let { handler.removeCallbacks(it) }
        runnable = Runnable {
            listener.onScrolled(recyclerView, dx, dy)
        }.also {
            handler.postDelayed(it, delayMs)
        }
    }
}