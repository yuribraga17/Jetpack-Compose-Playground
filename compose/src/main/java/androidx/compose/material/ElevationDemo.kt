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

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.InnerPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ElevationDemo() {
    Column {
        Box(Modifier.padding(20.dp).fillMaxWidth(), gravity = ContentGravity.Center) {
            val text = getMessage(MaterialTheme.colors.isLight)
            Text(text)
        }
        ScrollableColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = InnerPadding(25.dp)
        ) {
            elevations.forEach { elevation ->
                ElevatedCard(elevation)
            }
        }
    }
}

@Composable
private fun ElevatedCard(elevation: Dp) {
    Card(
        Modifier.padding(start = 10.dp, top = 20.dp, end = 10.dp, bottom = 20.dp),
        shape = RoundedCornerShape(4.dp),
        border = if (elevation == 0.dp) BorderStroke(1.dp, Color.Gray) else null,
        elevation = elevation
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .preferredHeight(150.dp)
                .clickable(onClick = {}),
            gravity = ContentGravity.Center
        ) {
            Text("$elevation", style = MaterialTheme.typography.h4)
        }
    }
}

private val elevations = listOf(
    0.dp,
    1.dp,
    2.dp,
    3.dp,
    4.dp,
    6.dp,
    8.dp,
    12.dp,
    16.dp,
    24.dp
)

private fun getMessage(isLight: Boolean) = (if (isLight) {
    "In a light theme elevation is represented by shadows"
} else {
    "In a dark theme elevation is represented by shadows and a translucent white overlay " +
            "applied to the surface"
}) + "\n\nnote: drawing a small border around 0.dp elevation to make it visible where the card " +
        "edges end"
