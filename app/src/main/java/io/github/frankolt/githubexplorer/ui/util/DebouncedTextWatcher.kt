package io.github.frankolt.githubexplorer.ui.util

import android.os.Handler
import android.text.Editable
import android.text.TextWatcher

class DebouncedTextWatcher(
    private val delayMs: Long,
    private val afterTextChanged: (text: Editable?) -> Unit
) : TextWatcher {

    private val handler = Handler()

    private var runnable: Runnable? = null

    override fun afterTextChanged(s: Editable?) {
        runnable?.let { handler.removeCallbacks(it) }
        runnable = Runnable {
            afterTextChanged.invoke(s)
        }.also {
            handler.postDelayed(it, delayMs)
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) = Unit
}
