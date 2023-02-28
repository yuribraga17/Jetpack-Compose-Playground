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

package androidx.compose.material.demos

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.InnerPadding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.TextButton
import androidx.compose.material.samples.ButtonSample
import androidx.compose.material.samples.ButtonWithIconSample
import androidx.compose.material.samples.FluidExtendedFab
import androidx.compose.material.samples.IconButtonSample
import androidx.compose.material.samples.IconToggleButtonSample
import androidx.compose.material.samples.OutlinedButtonSample
import androidx.compose.material.samples.SimpleExtendedFabNoIcon
import androidx.compose.material.samples.SimpleExtendedFabWithIcon
import androidx.compose.material.samples.SimpleFab
import androidx.compose.material.samples.TextButtonSample
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val DefaultSpace = 20.dp

@Composable
fun ButtonDemo() {
    ScrollableColumn(contentPadding = InnerPadding(10.dp)) {
        Buttons()
        Spacer(Modifier.preferredHeight(DefaultSpace))
        Fabs()
        Spacer(Modifier.preferredHeight(DefaultSpace))
        IconButtons()
        Spacer(Modifier.preferredHeight(DefaultSpace))
        CustomShapeButton()
    }
}

@Composable
private fun Buttons() {
    Text("Buttons")
    Spacer(Modifier.preferredHeight(DefaultSpace))
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        ButtonSample()
        OutlinedButtonSample()
        TextButtonSample()
    }

    Spacer(Modifier.preferredHeight(DefaultSpace))

    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(onClick = {}, backgroundColor = MaterialTheme.colors.secondary) {
            Text("Secondary Color")
        }
        ButtonWithIconSample()
    }

    Spacer(Modifier.preferredHeight(DefaultSpace))

    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Button(onClick = {}, enabled = false) {
            Text("Disabled")
        }
        OutlinedButton(onClick = {}, enabled = false) {
            Text("Disabled")
        }
        TextButton(onClick = {}, enabled = false) {
            Text("Disabled")
        }
    }
}

@Composable
private fun Fabs() {
    Text("Floating action buttons")
    Spacer(Modifier.preferredHeight(DefaultSpace))

    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        SimpleFab()
        SimpleExtendedFabNoIcon()
        SimpleExtendedFabWithIcon()
    }
    Spacer(Modifier.preferredHeight(DefaultSpace))
    FluidExtendedFab()
}

@Composable
private fun IconButtons() {
    Text("Icon buttons")
    Spacer(Modifier.preferredHeight(DefaultSpace))

    Row {
        IconButtonSample()
        IconToggleButtonSample()
    }
}

@Composable
private fun CustomShapeButton() {
    Text("Custom shape button")
    Spacer(Modifier.preferredHeight(DefaultSpace))
    OutlinedButton(
        onClick = {},
        modifier = Modifier.preferredSize(110.dp),
        shape = TriangleShape,
        backgroundColor = Color.Yellow,
        border = BorderStroke(width = 2.dp, color = Color.Black)
    ) {
        Column {
            Text("Click")
            Text("here")
        }
    }
}

private val TriangleShape = GenericShape { size ->
    moveTo(size.width / 2f, 0f)
    lineTo(size.width, size.height)
    lineTo(0f, size.height)
}
