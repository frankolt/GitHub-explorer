package io.github.frankolt.githubexplorer.ui.extensions

import android.text.Editable
import android.widget.EditText
import io.github.frankolt.githubexplorer.ui.util.DebouncedTextWatcher

/**
 * Updates the text only if necessary. This is to prevent calling the listeners when setting the
 * same text. Facilitates the MVVM architecture by not propagating an additional call to the
 * `ViewModel` when setting the text from the `LiveData`.
 *
 * (For example, when a user updates an input field, the `ViewModel` is notified and updates the
 * appropriate `LiveData` object. This results in updating the input field with the same text, which
 * results in notifying the `ViewModel` again. The loop stops here, because the `LiveData` will not
 * update the field again. However, this is undesirable if, for example, an API call is done on a
 * text update in the `ViewModel`.)
 */
fun EditText.update(text: CharSequence) {
    if (this.text.toString() != text) {
        this.setText(text)
    }
}

fun EditText.addDebouncedTextChangedListener(
    delayMs: Long,
    afterTextChanged: (text: Editable?) -> Unit
) {
    addTextChangedListener(DebouncedTextWatcher(delayMs, afterTextChanged))
}
