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

package androidx.compose.ui.demos

import androidx.compose.ui.demos.autofill.ExplicitAutofillTypesDemo
import androidx.compose.ui.demos.focus.FocusableDemo
import androidx.compose.ui.demos.focus.ReuseFocusRequester
import androidx.compose.ui.demos.gestures.DoubleTapGestureFilterDemo
import androidx.compose.ui.demos.gestures.DoubleTapInTapDemo
import androidx.compose.ui.demos.gestures.DragAndScaleGestureFilterDemo
import androidx.compose.ui.demos.gestures.DragGestureFilterDemo
import androidx.compose.ui.demos.gestures.DragSlopExceededGestureFilterDemo
import androidx.compose.ui.demos.gestures.HorizontalScrollersInVerticalScrollersDemo
import androidx.compose.ui.demos.gestures.LongPressDragGestureFilterDemo
import androidx.compose.ui.demos.gestures.LongPressGestureDetectorDemo
import androidx.compose.ui.demos.gestures.NestedLongPressDemo
import androidx.compose.ui.demos.gestures.NestedPressingDemo
import androidx.compose.ui.demos.gestures.NestedScalingDemo
import androidx.compose.ui.demos.gestures.NestedScrollingDemo
import androidx.compose.ui.demos.gestures.PointerInputDuringSubComp
import androidx.compose.ui.demos.gestures.PopupDragDemo
import androidx.compose.ui.demos.gestures.PressIndicatorGestureFilterDemo
import androidx.compose.ui.demos.gestures.RawDragGestureFilterDemo
import androidx.compose.ui.demos.gestures.ScaleGestureFilterDemo
import androidx.compose.ui.demos.gestures.ScrollGestureFilterDemo
import androidx.compose.ui.demos.gestures.TapGestureFilterDemo
import androidx.compose.ui.demos.gestures.VerticalScrollerInDrawerDemo
import androidx.compose.ui.demos.keyinput.KeyInputDemo
import androidx.compose.ui.demos.viewinterop.ViewInteropDemo
import androidx.ui.demos.common.ComposableDemo
import androidx.ui.demos.common.DemoCategory

private val GestureDemos = DemoCategory("Gestures", listOf(
    DemoCategory("Common Gestures", listOf(
        ComposableDemo("Press Indication") { PressIndicatorGestureFilterDemo() },
        ComposableDemo("Tap") { TapGestureFilterDemo() },
        ComposableDemo("Double Tap") { DoubleTapGestureFilterDemo() },
        ComposableDemo("Long Press") { LongPressGestureDetectorDemo() },
        ComposableDemo("Scroll") { ScrollGestureFilterDemo() },
        ComposableDemo("Drag") { DragGestureFilterDemo() },
        ComposableDemo("Long Press Drag") { LongPressDragGestureFilterDemo() },
        ComposableDemo("Scale") { ScaleGestureFilterDemo() }
    )),
    DemoCategory("Building Block Gestures", listOf(
        ComposableDemo("Drag Slop Exceeded") { DragSlopExceededGestureFilterDemo() },
        ComposableDemo("Raw Drag") { RawDragGestureFilterDemo() }
    )),
    DemoCategory(
        "Combinations / Case Studies", listOf(
            ComposableDemo("Nested Pressing") { NestedPressingDemo() },
            ComposableDemo("Horizontal Scrollers In Vertical Scroller") {
                HorizontalScrollersInVerticalScrollersDemo()
            },
            ComposableDemo("Vertical Scroller in Nav Drawer") {
                VerticalScrollerInDrawerDemo()
            },
            ComposableDemo("Nested Scrolling") { NestedScrollingDemo() },
            ComposableDemo("Nested Scaling") { NestedScalingDemo() },
            ComposableDemo("Drag and Scale") { DragAndScaleGestureFilterDemo() },
            ComposableDemo("Popup Drag") { PopupDragDemo() },
            ComposableDemo("Double Tap in Tap") { DoubleTapInTapDemo() },
            ComposableDemo("Nested Long Press") { NestedLongPressDemo() },
            ComposableDemo("Pointer Input During Sub Comp") { PointerInputDuringSubComp() }
        ))
))

private val FocusDemos = DemoCategory("Focus", listOf(
    ComposableDemo("Focusable Siblings") { FocusableDemo() },
    ComposableDemo("Reuse Focus Requester") { ReuseFocusRequester() }
    ))

private val GraphicsDemos = DemoCategory("Graphics", listOf(
    ComposableDemo("VectorGraphicsDemo") { VectorGraphicsDemo() },
    ComposableDemo("DeclarativeGraphicsDemo") { DeclarativeGraphicsDemo() }
))

val CoreDemos = DemoCategory("Framework", listOf(
    ComposableDemo("Explicit autofill types") { ExplicitAutofillTypesDemo() },
    FocusDemos,
    ComposableDemo("KeyInput") { KeyInputDemo() },
    ComposableDemo("Multiple collects measure") { MultipleCollectTest() },
    ComposableDemo("Popup") { PopupDemo() },
    GraphicsDemos,
    GestureDemos,
    ComposableDemo("Views interoperability") { ViewInteropDemo() },
    ComposableDemo("ViewModel") { ViewModelDemo() }
))
