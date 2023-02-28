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

package androidx.ui.demos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.onCommit
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.ui.demos.common.Demo
import androidx.compose.foundation.Icon
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.BaseTextField
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focusRequester
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

/**
 * A scrollable list of [launchableDemos], filtered by [filterText].
 */
@Composable
fun DemoFilter(launchableDemos: List<Demo>, filterText: String, onNavigate: (Demo) -> Unit) {
    val filteredDemos = launchableDemos
        .filter { it.title.contains(filterText, ignoreCase = true) }
        .sortedBy { it.title }
    ScrollableColumn {
        filteredDemos.forEach { demo ->
            FilteredDemoListItem(demo,
                filterText = filterText,
                onNavigate = {
                    onNavigate(it)
                }
            )
        }
    }
}

/**
 * [TopAppBar] with a text field allowing filtering all the demos.
 */
@Composable
fun FilterAppBar(
    filterText: TextFieldValue,
    onFilter: (TextFieldValue) -> Unit,
    onClose: () -> Unit
) {
    with(MaterialTheme.colors) {
        val appBarColor = if (isLight) {
            surface
        } else {
            // Blending primary over surface according to Material design guidance for brand
            // surfaces in dark theme
            primary.copy(alpha = 0.08f).compositeOver(surface)
        }
        TopAppBar(backgroundColor = appBarColor, contentColor = onSurface) {
            IconButton(modifier = Modifier.gravity(Alignment.CenterVertically), onClick = onClose) {
                Icon(Icons.Filled.Close)
            }
            FilterField(
                filterText,
                onFilter,
                Modifier.fillMaxWidth().gravity(Alignment.CenterVertically)
            )
        }
    }
}

/**
 * [BaseTextField] that edits the current [filterText], providing [onFilter] when edited.
 */
@Composable
@OptIn(
    ExperimentalFocus::class,
    ExperimentalFoundationApi::class
)
private fun FilterField(
    filterText: TextFieldValue,
    onFilter: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = FocusRequester()
    // TODO: replace with Material text field when available
    BaseTextField(
        modifier = modifier.focusRequester(focusRequester),
        value = filterText,
        onValueChange = onFilter
    )
    onCommit { focusRequester.requestFocus() }
}

/**
 * [ListItem] that displays a [demo] and highlights any matches for [filterText] inside [Demo.title]
 */
@Composable
private fun FilteredDemoListItem(
    demo: Demo,
    filterText: String,
    onNavigate: (Demo) -> Unit
) {
    val primary = MaterialTheme.colors.primary
    val annotatedString = annotatedString {
        val title = demo.title
        var currentIndex = 0
        val pattern = filterText.toRegex(option = RegexOption.IGNORE_CASE)
        pattern.findAll(title).forEach { result ->
            val index = result.range.first
            if (index > currentIndex) {
                append(title.substring(currentIndex, index))
                currentIndex = index
            }
            withStyle(SpanStyle(color = primary)) {
                append(result.value)
            }
            currentIndex = result.range.last + 1
        }
        if (currentIndex <= title.lastIndex) {
            append(title.substring(currentIndex, title.length))
        }
    }
    key(demo.title) {
        ListItem(
            text = {
                Text(
                    modifier = Modifier.preferredHeight(56.dp).wrapContentSize(Alignment.Center),
                    text = annotatedString
                )
            },
            modifier = Modifier.clickable { onNavigate(demo) }
        )
    }
}
