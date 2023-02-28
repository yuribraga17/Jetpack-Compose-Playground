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

package androidx.compose.material.demos

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.Button
import androidx.compose.material.samples.FancyIndicatorContainerTabs
import androidx.compose.material.samples.FancyIndicatorTabs
import androidx.compose.material.samples.FancyTabs
import androidx.compose.material.samples.IconTabs
import androidx.compose.material.samples.ScrollingFancyIndicatorContainerTabs
import androidx.compose.material.samples.ScrollingTextTabs
import androidx.compose.material.samples.TextAndIconTabs
import androidx.compose.material.samples.TextTabs
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TabDemo() {
    ScrollableColumn {
        val showingSimple = remember { mutableStateOf(true) }
        val buttonText = "Show ${if (showingSimple.value) "custom" else "simple"} tabs"

        Spacer(Modifier.height(24.dp))
        if (showingSimple.value) {
            TextTabs()
            Spacer(Modifier.height(24.dp))
            IconTabs()
            Spacer(Modifier.height(24.dp))
            TextAndIconTabs()
            Spacer(Modifier.height(24.dp))
            ScrollingTextTabs()
        } else {
            FancyTabs()
            Spacer(Modifier.height(24.dp))
            FancyIndicatorTabs()
            Spacer(Modifier.height(24.dp))
            FancyIndicatorContainerTabs()
            Spacer(Modifier.height(24.dp))
            ScrollingFancyIndicatorContainerTabs()
        }
        Spacer(Modifier.height(24.dp))
        Button(
            modifier = Modifier.gravity(Alignment.CenterHorizontally),
            onClick = {
                showingSimple.value = !showingSimple.value
            },
            backgroundColor = Color.Cyan
        ) {
            Text(buttonText)
        }
        Spacer(Modifier.preferredHeight(50.dp))
    }
}
