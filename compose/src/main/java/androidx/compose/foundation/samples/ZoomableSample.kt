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
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberZoomableController
import androidx.compose.foundation.gestures.zoomable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Sampled
@Composable
fun ZoomableSample() {
    Box(
        Modifier.preferredSize(300.dp).clipToBounds(),
        backgroundColor = Color.LightGray
    ) {
        var scale by remember { mutableStateOf(1f) }
        val zoomableController = rememberZoomableController { scale *= it }
        Box(
            Modifier
                .zoomable(zoomableController)
                .clickable(
                    indication = null,
                    onDoubleClick = { zoomableController.smoothScaleBy(4f) },
                    onClick = {}
                )
                .fillMaxSize()
                .border(1.dp, Color.Green),
            gravity = ContentGravity.Center
        ) {
            Text(
                "☠",
                fontSize = 32.sp,
                modifier = Modifier.drawLayer(scaleX = scale, scaleY = scale)
            )
        }
    }
}