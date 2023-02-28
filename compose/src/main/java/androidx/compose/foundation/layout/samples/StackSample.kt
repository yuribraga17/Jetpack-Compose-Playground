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
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Sampled
@Composable
fun SimpleStack() {
    Stack {
        Box(Modifier.fillMaxSize(), backgroundColor = Color.Cyan)
        Box(
            Modifier.matchParentSize().padding(top = 20.dp, bottom = 20.dp),
            backgroundColor = Color.Yellow
        )
        Box(Modifier.matchParentSize().padding(40.dp), backgroundColor = Color.Magenta)
        Box(
            Modifier.gravity(Alignment.Center).preferredSize(300.dp, 300.dp),
            backgroundColor = Color.Green
        )
        Box(
            Modifier.gravity(Alignment.TopStart).preferredSize(150.dp, 150.dp),
            backgroundColor = Color.Red
        )
        Box(
            Modifier.gravity(Alignment.BottomEnd).preferredSize(150.dp, 150.dp),
            backgroundColor = Color.Blue
        )
    }
}