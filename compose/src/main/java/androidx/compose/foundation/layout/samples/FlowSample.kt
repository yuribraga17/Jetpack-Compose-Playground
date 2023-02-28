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

package androidx.compose.foundation.layout.samples

import androidx.annotation.Sampled
import androidx.compose.foundation.Box
import androidx.compose.foundation.layout.ExperimentalLayout
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

val sizes = listOf(
    50.dp, 50.dp, 50.dp, 30.dp, 40.dp, 110.dp, 100.dp, 40.dp, 30.dp,
    20.dp, 70.dp, 50.dp, 100.dp, 20.dp, 60.dp, 60.dp, 50.dp, 60.dp
)

@Sampled
@Composable
@OptIn(ExperimentalLayout::class)
fun SimpleFlowRow() {
    FlowRow(
        mainAxisSpacing = 10.dp,
        crossAxisSpacing = 10.dp
    ) {
        sizes.forEach { size ->
            Box(Modifier.preferredSize(size, 20.dp), backgroundColor = Color.Magenta)
        }
    }
}

@Sampled
@Composable
@OptIn(ExperimentalLayout::class)
fun SimpleFlowColumn() {
    FlowColumn(
        mainAxisSpacing = 10.dp,
        crossAxisSpacing = 10.dp
    ) {
        sizes.forEach { size ->
            Box(Modifier.preferredSize(20.dp, size), backgroundColor = Color.Magenta)
        }
    }
}