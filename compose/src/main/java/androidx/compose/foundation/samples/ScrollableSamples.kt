/*
 * Copyright 2020 The Android Open Source Project
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

package androidx.compose.foundation.samples

import androidx.annotation.Sampled
import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Text
import androidx.compose.foundation.gestures.rememberScrollableController
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.gesture.scrollorientationlocking.Orientation
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.roundToInt

@Sampled
@Composable
fun ScrollableSample() {
    // actual composable state
    val offset = remember { mutableStateOf(0f) }
    // state for Scrollable, describes how to consume scrolling delta and update offset
    Box(
        Modifier
            .preferredSize(200.dp)
            .scrollable(
                orientation = Orientation.Vertical,
                controller = rememberScrollableController { delta ->
                    offset.value = offset.value + delta
                    delta
                }
            ),
        backgroundColor = Color.LightGray,
        gravity = ContentGravity.Center
    ) {
        Text(offset.value.roundToInt().toString(), style = TextStyle(fontSize = 50.sp))
    }
}