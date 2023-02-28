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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.layout.preferredWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Layout
import androidx.compose.ui.Modifier
import androidx.compose.ui.VerticalAlignmentLine
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max
import kotlin.math.min

@Sampled
@Composable
fun SimpleColumn() {
    Column {
        // The child with no weight will have the specified size.
        Box(Modifier.preferredSize(40.dp, 80.dp), backgroundColor = Color.Magenta)
        // Has weight, the child will occupy half of the remaining height.
        Box(Modifier.preferredWidth(40.dp).weight(1f), backgroundColor = Color.Yellow)
        // Has weight and does not fill, the child will occupy at most half of the remaining height.
        // Therefore it will occupy 80.dp (its preferred height) if the assigned height is larger.
        Box(
            Modifier.preferredSize(40.dp, 80.dp)
                .weight(1f, fill = false),
            backgroundColor = Color.Green
        )
    }
}

@Sampled
@Composable
fun SimpleGravityInColumn() {
    Column(Modifier.fillMaxWidth()) {
        // The child with no gravity modifier is positioned by default so that its start edge
        // aligned with the start edge of the horizontal axis.
        Box(Modifier.preferredSize(80.dp, 40.dp), backgroundColor = Color.Magenta)
        // Gravity.Start, the child will be positioned so that its start edge is aligned with
        // the start edge of the horizontal axis.
        Box(
            Modifier.preferredSize(80.dp, 40.dp)
                .gravity(Alignment.Start),
            backgroundColor = Color.Red
        )
        // Gravity.Center, the child will be positioned so that its center is in the middle of
        // the horizontal axis.
        Box(
            Modifier.preferredSize(80.dp, 40.dp)
                .gravity(Alignment.CenterHorizontally),
            backgroundColor = Color.Yellow
        )
        // Gravity.End, the child will be positioned so that its end edge aligned to the end of
        // the horizontal axis.
        Box(
            Modifier.preferredSize(80.dp, 40.dp)
                .gravity(Alignment.End),
            backgroundColor = Color.Green
        )
    }
}

@Sampled
@Composable
fun SimpleRelativeToSiblings() {
    Column {
        // Center of the first rectangle is aligned to the right edge of the second rectangle and
        // left edge of the third one.
        Box(
            Modifier.preferredSize(80.dp, 40.dp).alignWithSiblings { it.width / 2 },
            backgroundColor = Color.Blue
        )
        Box(
            Modifier.preferredSize(80.dp, 40.dp).alignWithSiblings { it.width },
            backgroundColor = Color.Magenta
        )
        Box(
            Modifier.preferredSize(80.dp, 40.dp).alignWithSiblings { 0 },
            backgroundColor = Color.Red
        )
    }
}

@Sampled
@Composable
fun SimpleRelativeToSiblingsInColumn() {
    // Alignment lines provided by the RectangleWithStartEnd layout. We need to create these
    // local alignment lines because Compose is currently not providing any top-level out of
    // the box vertical alignment lines. Two horizontal alignment lines are provided though:
    // FirstBaseline and LastBaseline, but these can only be used to align vertically children
    // of Row because the baselines are horizontal. Therefore, we create these vertical alignment
    // lines, that refer to the start and end of the RectangleWithStartEnd layout which knows
    // how to provide them. Then Column will know how to align horizontally children such
    // that the positions of the alignment lines coincide, as asked by alignWithSiblings.
    val start = VerticalAlignmentLine(::min)
    val end = VerticalAlignmentLine(::min)

    @Composable
    fun RectangleWithStartEnd(modifier: Modifier = Modifier, color: Color, width: Dp, height: Dp) {
        Layout(
            children = { },
            modifier = modifier.background(color = color)
        ) { _, constraints ->
            val widthPx = max(width.toIntPx(), constraints.minWidth)
            val heightPx = max(height.toIntPx(), constraints.minHeight)
            layout(widthPx, heightPx, mapOf(start to 0, end to widthPx)) {}
        }
    }

    Column {
        // Center of the first rectangle is aligned to the right edge of the second rectangle and
        // left edge of the third one.
        Box(
            Modifier.preferredSize(80.dp, 40.dp).alignWithSiblings { it.width / 2 },
            backgroundColor = Color.Blue
        )
        RectangleWithStartEnd(
            Modifier.alignWithSiblings(end),
            color = Color.Magenta,
            width = 80.dp,
            height = 40.dp
        )
        RectangleWithStartEnd(
            Modifier.alignWithSiblings(start),
            color = Color.Red,
            width = 80.dp,
            height = 40.dp
        )
    }
}