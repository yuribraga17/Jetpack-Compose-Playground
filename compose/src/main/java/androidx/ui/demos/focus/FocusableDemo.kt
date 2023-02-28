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
package androidx.compose.ui.demos.focus

import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.isFocused
import androidx.compose.ui.focusObserver
import androidx.compose.ui.focusRequester
import androidx.compose.ui.gesture.tapGestureFilter
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green

@Composable
fun FocusableDemo() {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        CenteredRow {
            Text("Click on any focusable to bring it into focus:")
        }
        CenteredRow {
            FocusableText("Focusable 1")
        }
        CenteredRow {
            FocusableText("Focusable 2")
        }
        CenteredRow {
            FocusableText("Focusable 3")
        }
    }
}

@Composable
@OptIn(ExperimentalFocus::class)
private fun FocusableText(text: String) {
    var color by remember { mutableStateOf(Black) }
    val focusRequester = FocusRequester()
    Text(
        modifier = Modifier
            .focusRequester(focusRequester)
            .focusObserver { color = if (it.isFocused) Green else Black }
            .focus()
            .tapGestureFilter { focusRequester.requestFocus() },
        text = text,
        color = color
    )
}

@Composable
private fun CenteredRow(children: @Composable RowScope.() -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        children = children
    )
}