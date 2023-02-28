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

package androidx.compose.foundation.layout.demos

import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SimpleLayoutDemo() {
    val lightGrey = Color(0xFFCFD8DC)
    Column {
        Text(text = "Row", fontSize = 48.sp)

        Stack(Modifier.preferredWidth(ExampleSize).background(color = lightGrey)) {
            Row(Modifier.fillMaxWidth()) {
                PurpleSquare()
                CyanSquare()
            }
        }
        Spacer(Modifier.preferredHeight(24.dp))
        Stack(Modifier.preferredWidth(ExampleSize).background(color = lightGrey)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                PurpleSquare()
                CyanSquare()
            }
        }
        Spacer(Modifier.preferredHeight(24.dp))
        Stack(Modifier.preferredWidth(ExampleSize).background(color = lightGrey)) {
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                PurpleSquare()
                CyanSquare()
            }
        }
        Spacer(Modifier.preferredHeight(24.dp))
        Stack(Modifier.preferredWidth(ExampleSize).background(color = lightGrey)) {
            Row(Modifier.fillMaxWidth()) {
                PurpleSquare()
                CyanSquare()
            }
        }
        Spacer(Modifier.preferredHeight(24.dp))
        Stack(Modifier.preferredWidth(ExampleSize).background(color = lightGrey)) {
            Row(Modifier.fillMaxWidth()) {
                PurpleSquare(Modifier.gravity(Alignment.Bottom))
                CyanSquare(Modifier.gravity(Alignment.Bottom))
            }
        }
        Spacer(Modifier.preferredHeight(24.dp))
        Text(text = "Column", fontSize = 48.sp)
        Row(Modifier.fillMaxWidth()) {
            Stack(Modifier.preferredHeight(ExampleSize).background(color = lightGrey)) {
                Column(Modifier.fillMaxHeight()) {
                    PurpleSquare()
                    CyanSquare()
                }
            }
            Spacer(Modifier.preferredWidth(24.dp))
            Stack(Modifier.preferredHeight(ExampleSize).background(color = lightGrey)) {
                Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
                    PurpleSquare()
                    CyanSquare()
                }
            }
            Spacer(Modifier.preferredWidth(24.dp))
            Stack(Modifier.preferredHeight(ExampleSize).background(color = lightGrey)) {
                Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Bottom) {
                    PurpleSquare()
                    CyanSquare()
                }
            }
            Spacer(Modifier.preferredWidth(24.dp))
            Stack(Modifier.preferredHeight(ExampleSize).background(color = lightGrey)) {
                Column(Modifier.fillMaxHeight()) {
                    PurpleSquare()
                    CyanSquare()
                }
            }
            Spacer(Modifier.preferredWidth(24.dp))
            Stack(Modifier.preferredHeight(ExampleSize).background(color = lightGrey)) {
                Column(Modifier.fillMaxHeight()) {
                    PurpleSquare(Modifier.gravity(Alignment.End))
                    CyanSquare(Modifier.gravity(Alignment.End))
                }
            }
        }
    }
}

@Composable
private fun PurpleSquare(modifier: Modifier = Modifier) {
    Box(modifier.preferredSize(48.dp), backgroundColor = Color(0xFF6200EE))
}

@Composable
private fun CyanSquare(modifier: Modifier = Modifier) {
    Box(modifier.preferredSize(24.dp), backgroundColor = Color(0xFF03DAC6))
}

private val ExampleSize = 140.dp