/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.compose.ui.demos.gestures

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.pressIndicatorGestureFilter
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.unit.dp

/**
 * Simple [pressIndicatorGestureFilter] demo.
 */
@Composable
fun PressIndicatorGestureFilterDemo() {
    val pressed = remember { mutableStateOf(false) }

    val onStart: (Offset) -> Unit = {
        pressed.value = true
    }

    val onStop = {
        pressed.value = false
    }

    val color =
        if (pressed.value) {
            PressedColor.compositeOver(Grey)
        } else {
            Grey
        }

    Column {
        Text("The box shows press indication when you press it.")
        Box(
            Modifier.fillMaxSize()
                .wrapContentSize(Alignment.Center)
                .preferredSize(192.dp)
                .pressIndicatorGestureFilter(onStart, onStop, onStop),
            backgroundColor = color,
            border = BorderStroke(2.dp, BorderColor)
        )
    }
}
