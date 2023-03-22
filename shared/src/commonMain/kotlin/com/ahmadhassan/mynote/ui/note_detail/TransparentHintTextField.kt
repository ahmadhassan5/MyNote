package com.ahmadhassan.mynote.ui.note_detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.TextStyle

/**
 * Created by Ahmad Hassan on 13/12/2022.
 */

@Composable
internal fun TransparentHintTextField(
    text: String,
    hint: String,
    isHintVisible: Boolean,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(),
    isSingleLine: Boolean = false,
    onFocusChanged: (FocusState) -> Unit
) {
    Box(
        modifier = modifier
    ) {
        BasicTextField(
            value = text,
            onValueChange = onValueChanged,
            singleLine = isSingleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged {
                    onFocusChanged(it)
                }
        )

        if (isHintVisible)
            Text(
                text = hint,
                style = textStyle
            )

    }

}