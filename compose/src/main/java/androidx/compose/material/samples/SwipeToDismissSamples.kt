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

package androidx.compose.material.samples

import androidx.annotation.Sampled
import androidx.compose.animation.animate
import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection.EndToStart
import androidx.compose.material.DismissDirection.StartToEnd
import androidx.compose.material.DismissValue.Default
import androidx.compose.material.DismissValue.DismissedToEnd
import androidx.compose.material.DismissValue.DismissedToStart
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.ListItem
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.drawLayer
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

private val items = listOf(
    "Cupcake",
    "Donut",
    "Eclair",
    "Froyo",
    "Gingerbread",
    "Honeycomb",
    "Ice cream sandwich",
    "Jelly bean",
    "KitKat",
    "Lollipop",
    "Marshmallow",
    "Nougat",
    "Oreo",
    "Pie"
)

@Sampled
@Composable
@OptIn(ExperimentalMaterialApi::class)
fun SwipeToDismissListItems() {
    // This is an example of a list of dismissible items, similar to what you would see in an
    // email app. Swiping left reveals a 'delete' icon and swiping right reveals a 'done' icon.
    // The background will start as grey, but once the dismiss threshold is reached, the colour
    // will animate to red if you're swiping left or green if you're swiping right. When you let
    // go, the item will animate out of the way if you're swiping left (like deleting an email) or
    // back to its default position if you're swiping right (like marking an email as read/unread).
    LazyColumnFor(items) { item ->
        var unread by remember { mutableStateOf(false) }
        val dismissState = rememberDismissState(
            confirmStateChange = {
                if (it == DismissedToEnd) unread = !unread
                it != DismissedToEnd
            }
        )
        SwipeToDismiss(
            state = dismissState,
            modifier = Modifier.padding(vertical = 4.dp),
            directions = setOf(StartToEnd, EndToStart),
            dismissThresholds = { direction ->
                FractionalThreshold(if (direction == StartToEnd) 0.25f else 0.5f)
            },
            background = {
                val direction = dismissState.dismissDirection ?: return@SwipeToDismiss
                val color = animate(when (dismissState.targetValue) {
                    Default -> Color.LightGray
                    DismissedToEnd -> Color.Green
                    DismissedToStart -> Color.Red
                })
                val gravity = when (direction) {
                    StartToEnd -> ContentGravity.CenterStart
                    EndToStart -> ContentGravity.CenterEnd
                }
                val icon = when (direction) {
                    StartToEnd -> Icons.Default.Done
                    EndToStart -> Icons.Default.Delete
                }
                val scale = animate(if (dismissState.targetValue == Default) 0.75f else 1f)

                Box(
                    modifier = Modifier.fillMaxSize(),
                    backgroundColor = color,
                    paddingStart = 20.dp,
                    paddingEnd = 20.dp,
                    gravity = gravity
                ) {
                    Icon(icon, Modifier.drawLayer(scaleX = scale, scaleY = scale))
                }
            },
            dismissContent = {
                Card(
                    elevation = animate(if (dismissState.dismissDirection != null) 4.dp else 0.dp)
                ) {
                    ListItem(
                        text = { Text(item, fontWeight = if (unread) FontWeight.Bold else null) },
                        secondaryText = { Text("Swipe me left or right!") }
                    )
                }
            }
        )
    }
}
