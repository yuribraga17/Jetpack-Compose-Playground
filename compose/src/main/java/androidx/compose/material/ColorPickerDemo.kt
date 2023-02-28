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

import android.graphics.SweepGradient
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.FloatPropKey
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Box
import androidx.compose.foundation.ContentGravity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.currentTextStyle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Stack
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.emptyContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.WithConstraints
import androidx.compose.ui.draw.drawOpacity
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Radius
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.gesture.DragObserver
import androidx.compose.ui.gesture.dragGestureFilter
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.isSet
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.toPixelMap
import androidx.compose.ui.platform.DensityAmbient
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import java.util.Locale

/**
 * Demo that shows picking a color from a color wheel, which then dynamically updates
 * the color of a [TopAppBar]. This pattern could also be used to update the value of a
 * Colors, updating the overall theme for an application.
 */
@Composable
fun ColorPickerDemo() {
    var primary by remember { mutableStateOf(Color(0xFF6200EE)) }
    Surface(color = Color(0xFF121212)) {
        Column {
            TopAppBar(title = { Text("Color Picker") }, backgroundColor = primary)
            ColorPicker(onColorChange = { primary = it })
        }
    }
}

@Composable
private fun ColorPicker(onColorChange: (Color) -> Unit) {
    WithConstraints(
        Modifier.padding(50.dp)
            .fillMaxSize()
            .aspectRatio(1f)
    ) {
        val diameter = constraints.maxWidth
        var position by remember { mutableStateOf(Offset.Zero) }
        val colorWheel = remember(diameter) { ColorWheel(diameter) }

        var isDragging by remember { mutableStateOf(false) }
        val inputModifier = SimplePointerInput(
            position = position,
            onPositionChange = { newPosition ->
                // Work out if the new position is inside the circle we are drawing, and has a
                // valid color associated to it. If not, keep the current position
                val newColor = colorWheel.colorForPosition(newPosition)
                if (newColor.isSet) {
                    position = newPosition
                    onColorChange(newColor)
                }
            },
            onDragStateChange = { isDragging = it }
        )

        Stack(Modifier.fillMaxSize()) {
            Image(modifier = inputModifier, asset = colorWheel.image)
            val color = colorWheel.colorForPosition(position)
            if (color.isSet) {
                Magnifier(visible = isDragging, position = position, color = color)
            }
        }
    }
}

// TODO: b/152046065 dragging has the wrong semantics here, and it's possible to continue dragging
// outside the bounds of the layout. Use a higher level, simple input wrapper when it's available
// to just get the current position of the pointer, without needing to care about drag behavior /
// relative positions.
/**
 * [dragGestureFilter] that only cares about raw positions, where [position] is the position of
 * the current / last input event, [onPositionChange] is called with the new position when the
 * pointer moves, and [onDragStateChange] is called when dragging starts / stops.
 */
@Composable
private fun SimplePointerInput(
    position: Offset,
    onPositionChange: (Offset) -> Unit,
    onDragStateChange: (Boolean) -> Unit
): Modifier {
    val observer = object : DragObserver {
        override fun onStart(downPosition: Offset) {
            onDragStateChange(true)
            onPositionChange(downPosition)
        }

        override fun onDrag(dragDistance: Offset): Offset {
            onPositionChange(position + dragDistance)
            return dragDistance
        }

        override fun onCancel() {
            onDragStateChange(false)
        }

        override fun onStop(velocity: Offset) {
            onDragStateChange(false)
        }
    }

    return Modifier.dragGestureFilter(observer, startDragImmediately = true)
}

/**
 * Magnifier displayed on top of [position] with the currently selected [color].
 */
@Composable
private fun Magnifier(visible: Boolean, position: Offset, color: Color) {
    val offset = with(DensityAmbient.current) {
        Modifier.offset(
            position.x.toDp() - MagnifierWidth / 2,
            // Align with the center of the selection circle
            position.y.toDp() - (MagnifierHeight - (SelectionCircleDiameter / 2))
        )
    }
    MagnifierTransition(
        visible,
        MagnifierWidth,
        SelectionCircleDiameter
    ) { labelWidth: Dp, selectionDiameter: Dp,
        opacity: Float ->
        Column(
            offset.preferredSize(width = MagnifierWidth, height = MagnifierHeight)
                .drawOpacity(opacity)
        ) {
            Box(Modifier.fillMaxWidth(), gravity = ContentGravity.Center) {
                MagnifierLabel(Modifier.preferredSize(labelWidth, MagnifierLabelHeight), color)
            }
            Spacer(Modifier.weight(1f))
            Box(
                Modifier.fillMaxWidth().preferredHeight(SelectionCircleDiameter),
                gravity = ContentGravity.Center
            ) {
                MagnifierSelectionCircle(Modifier.preferredSize(selectionDiameter), color)
            }
        }
    }
}

private val MagnifierWidth = 110.dp
private val MagnifierHeight = 100.dp
private val MagnifierLabelHeight = 50.dp
private val SelectionCircleDiameter = 30.dp

/**
 * [transition] that animates between [visible] states of the magnifier by animating the width of
 * the label, diameter of the selection circle, and opacity of the overall magnifier
 */
@Composable
private fun MagnifierTransition(
    visible: Boolean,
    maxWidth: Dp,
    maxDiameter: Dp,
    children: @Composable (labelWidth: Dp, selectionDiameter: Dp, opacity: Float) -> Unit
) {
    val transitionDefinition = remember {
        transitionDefinition<Boolean> {
            state(false) {
                this[LabelWidthPropKey] = 0.dp
                this[MagnifierDiameterPropKey] = 0.dp
                this[OpacityPropKey] = 0f
            }
            state(true) {
                this[LabelWidthPropKey] = maxWidth
                this[MagnifierDiameterPropKey] = maxDiameter
                this[OpacityPropKey] = 1f
            }
            transition(false to true) {
                LabelWidthPropKey using tween()
                MagnifierDiameterPropKey using tween()
                OpacityPropKey using tween()
            }
            transition(true to false) {
                LabelWidthPropKey using tween()
                MagnifierDiameterPropKey using tween()
                OpacityPropKey using tween(
                    delayMillis = 100,
                    durationMillis = 200
                )
            }
        }
    }
    val state = transition(transitionDefinition, visible)
    children(state[LabelWidthPropKey], state[MagnifierDiameterPropKey], state[OpacityPropKey])
}

private val LabelWidthPropKey = DpPropKey()
private val MagnifierDiameterPropKey = DpPropKey()
private val OpacityPropKey = FloatPropKey()

/**
 * Label representing the currently selected [color], with [Text] representing the hex code and a
 * square at the start showing the [color].
 */
@Composable
private fun MagnifierLabel(modifier: Modifier, color: Color) {
    Surface(shape = MagnifierPopupShape, elevation = 4.dp) {
        Row(modifier) {
            Box(Modifier.weight(0.25f).fillMaxHeight(), backgroundColor = color)
            // Add `#` and drop alpha characters
            val text = "#" + Integer.toHexString(color.toArgb()).toUpperCase(Locale.ROOT).drop(2)
            val textStyle = currentTextStyle().copy(textAlign = TextAlign.Center)
            Text(
                text = text,
                modifier = Modifier.weight(0.75f).padding(top = 10.dp, bottom = 20.dp),
                style = textStyle,
                maxLines = 1
            )
        }
    }
}

/**
 * Selection circle drawn over the currently selected pixel of the color wheel.
 */
@Composable
private fun MagnifierSelectionCircle(modifier: Modifier, color: Color) {
    Surface(
        modifier,
        shape = CircleShape,
        elevation = 4.dp,
        color = color,
        border = BorderStroke(2.dp, SolidColor(Color.Black.copy(alpha = 0.75f))),
        content = emptyContent()
    )
}

/**
 * A [GenericShape] that draws a box with a triangle at the bottom center to indicate a popup.
 */
private val MagnifierPopupShape = GenericShape { size ->
    val width = size.width
    val height = size.height

    val arrowY = height * 0.8f
    val arrowXOffset = width * 0.4f

    addRoundRect(RoundRect(0f, 0f, width, arrowY, radius = Radius(20f, 20f)))

    moveTo(arrowXOffset, arrowY)
    lineTo(width / 2f, height)
    lineTo(width - arrowXOffset, arrowY)
    close()
}

/**
 * A color wheel with an [ImageAsset] that draws a circular color wheel of the specified diameter.
 */
private class ColorWheel(diameter: Int) {
    private val radius = diameter / 2f

    // TODO: b/152063545 - replace with Compose SweepGradient when it is available
    private val sweepShader = SweepGradient(
        radius,
        radius,
        intArrayOf(
            android.graphics.Color.RED,
            android.graphics.Color.MAGENTA,
            android.graphics.Color.BLUE,
            android.graphics.Color.CYAN,
            android.graphics.Color.GREEN,
            android.graphics.Color.YELLOW,
            android.graphics.Color.RED
        ),
        null
    )

    val image = ImageAsset(diameter, diameter).also { asset ->
        val canvas = Canvas(asset)
        val center = Offset(radius, radius)
        val paint = Paint().apply { shader = sweepShader }
        canvas.drawCircle(center, radius, paint)
    }
}

/**
 * @return the matching color for [position] inside [ColorWheel], or `null` if there is no color
 * or the color is partially transparent.
 */
private fun ColorWheel.colorForPosition(position: Offset): Color {
    val x = position.x.toInt().coerceAtLeast(0)
    val y = position.y.toInt().coerceAtLeast(0)
    with(image.toPixelMap()) {
        if (x >= width || y >= height) return Color.Unset
        return this[x, y].takeIf { it.alpha == 1f } ?: Color.Unset
    }
}
