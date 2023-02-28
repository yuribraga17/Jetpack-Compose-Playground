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

import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.gesture.LongPressDragObserver
import androidx.compose.ui.gesture.longPressDragGestureFilter
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.unit.dp

/**
 * Simple [longPressDragGestureFilter] demo.
 */
@Composable
fun LongPressDragGestureFilterDemo() {

    val offset = remember { mutableStateOf(Offset.Zero) }
    val color = remember { mutableStateOf(Grey) }

    val longPressDragObserver =
        object : LongPressDragObserver {

            override fun onLongPress(pxPosition: Offset) {
                color.value = Red
            }

            override fun onDragStart() {
                super.onDragStart()
                color.value = Blue
            }

            override fun onDrag(dragDistance: Offset): Offset {
                offset.value += dragDistance
                return dragDistance
            }

            override fun onStop(velocity: Offset) {
                color.value = Grey
            }
        }

    val (offsetX, offsetY) =
        with(DensityAmbient.current) { offset.value.x.toDp() to offset.value.y.toDp() }

    Column {
        Text("Demonstrates dragging that only begins once a long press has occurred!")
        Text("Dragging only occurs once you have long pressed on the box.")
        Box(
            Modifier.offset(offsetX, offsetY)
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
                .preferredSize(192.dp)
                .longPressDragGestureFilter(longPressDragObserver),
            backgroundColor = color.value
        )
    }
}