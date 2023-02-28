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

package androidx.compose.ui.demos.autofill

import android.graphics.Rect
import androidx.compose.foundation.BaseTextField
import androidx.compose.foundation.Box
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.AutofillNode
import androidx.compose.ui.autofill.AutofillType
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.isFocused
import androidx.compose.ui.focusObserver
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.toComposeRect
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.onPositioned
import androidx.compose.ui.platform.AutofillAmbient
import androidx.compose.ui.platform.AutofillTreeAmbient
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
@OptIn(
    ExperimentalFocus::class,
    ExperimentalFoundationApi::class
)
fun ExplicitAutofillTypesDemo() {
    Column {
        val nameState = remember { mutableStateOf(TextFieldValue("Enter name here")) }
        val emailState = remember { mutableStateOf(TextFieldValue("Enter email here")) }
        val autofill = AutofillAmbient.current
        val labelStyle = MaterialTheme.typography.subtitle1
        val textStyle = MaterialTheme.typography.h6

        Text("Name", style = labelStyle)
        Autofill(
            autofillTypes = listOf(AutofillType.PersonFullName),
            onFill = { nameState.value = TextFieldValue(it) }
        ) { autofillNode ->
            BaseTextField(
                modifier = Modifier.focusObserver {
                    autofill?.apply {
                        if (it.isFocused) {
                            requestAutofillForNode(autofillNode)
                        } else {
                            cancelAutofillForNode(autofillNode)
                        }
                    }
                },
                value = nameState.value,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Unspecified,
                onValueChange = { nameState.value = it },
                textStyle = textStyle
            )
        }

        Spacer(Modifier.preferredHeight(40.dp))

        Text("Email", style = labelStyle)
        Autofill(
            autofillTypes = listOf(AutofillType.EmailAddress),
            onFill = { emailState.value = TextFieldValue(it) }
        ) { autofillNode ->
            BaseTextField(
                modifier = Modifier.focusObserver {
                    autofill?.run {
                        if (it.isFocused) {
                            requestAutofillForNode(autofillNode)
                        } else {
                            cancelAutofillForNode(autofillNode)
                        }
                    }
                },
                value = emailState.value,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Unspecified,
                onValueChange = { emailState.value = it },
                textStyle = textStyle
            )
        }
    }
}

@Composable
private fun Autofill(
    autofillTypes: List<AutofillType>,
    onFill: ((String) -> Unit),
    children: @Composable (AutofillNode) -> Unit
) {
    val autofillNode = AutofillNode(onFill = onFill, autofillTypes = autofillTypes)

    val autofillTree = AutofillTreeAmbient.current
    autofillTree += autofillNode

    Box(Modifier.onPositioned {
        autofillNode.boundingBox = it.boundingBox().toComposeRect()
    }) {
        children(autofillNode)
    }
}

private fun LayoutCoordinates.boundingBox() = localToGlobal(Offset.Zero).run {
    Rect(
        x.toInt(),
        y.toInt(),
        x.toInt() + size.width,
        y.toInt() + size.height
    )
}