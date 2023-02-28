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

package androidx.compose.material.samples

import androidx.annotation.Sampled
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.contentColor
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageAsset
import androidx.compose.ui.graphics.vector.VectorAsset

@Sampled
@Composable
fun OneLineListItems(
    icon24x24: ImageAsset,
    icon40x40: ImageAsset,
    icon56x56: ImageAsset,
    vectorIcon: VectorAsset
) {
    Column {
        ListItem(text = { Text("One line list item with no icon") })
        Divider()
        ListItem(
            text = { Text("One line list item with 24x24 icon") },
            icon = { Image(icon24x24, colorFilter = ColorFilter.tint(contentColor())) }
        )
        Divider()
        ListItem(
            text = { Text("One line list item with 40x40 icon") },
            icon = { Image(icon40x40, colorFilter = ColorFilter.tint(contentColor())) }
        )
        Divider()
        ListItem(
            text = { Text("One line list item with 56x56 icon") },
            icon = { Image(icon56x56, colorFilter = ColorFilter.tint(contentColor())) }
        )
        Divider()
        ListItem(
            text = { Text("One line clickable list item") },
            icon = { Image(icon56x56, colorFilter = ColorFilter.tint(contentColor())) },
            modifier = Modifier.clickable { }
        )
        Divider()
        ListItem(
            text = { Text("One line list item with trailing icon") },
            trailing = { Icon(vectorIcon) }
        )
        Divider()
        ListItem(
            text = { Text("One line list item") },
            icon = { Image(icon40x40, colorFilter = ColorFilter.tint(contentColor())) },
            trailing = { Icon(vectorIcon) }
        )
        Divider()
    }
}

@Sampled
@Composable
// TODO(popam, b/159689286): material icons instead of ImageAsset when they can have custom sizes
fun TwoLineListItems(icon24x24: ImageAsset, icon40x40: ImageAsset) {
    Column {
        ListItem(
            text = { Text("Two line list item") },
            secondaryText = { Text("Secondary text") }
        )
        Divider()
        ListItem(
            text = { Text("Two line list item") },
            overlineText = { Text("OVERLINE") }
        )
        Divider()
        ListItem(
            text = { Text("Two line list item with 24x24 icon") },
            secondaryText = { Text("Secondary text") },
            icon = { Image(icon24x24, colorFilter = ColorFilter.tint(contentColor())) }
        )
        Divider()
        ListItem(
            text = { Text("Two line list item with 40x40 icon") },
            secondaryText = { Text("Secondary text") },
            icon = { Image(icon40x40, colorFilter = ColorFilter.tint(contentColor())) }
        )
        Divider()
        ListItem(
            text = { Text("Two line list item with 40x40 icon") },
            secondaryText = { Text("Secondary text") },
            trailing = { Text("meta") },
            icon = { Image(icon40x40, colorFilter = ColorFilter.tint(contentColor())) }
        )
        Divider()
        var checked by remember { mutableStateOf(false) }
        ListItem(
            text = { Text("Two line list item") },
            secondaryText = { Text("Secondary text") },
            icon = { Image(icon40x40, colorFilter = ColorFilter.tint(contentColor())) },
            trailing = {
                Checkbox(checked, onCheckedChange = { checked = !checked })
            }
        )
        Divider()
    }
}

@Sampled
@Composable
fun ThreeLineListItems(icon24x24: ImageAsset, vectorIcon: VectorAsset) {
    Column {
        ListItem(
            text = { Text("Three line list item") },
            secondaryText = {
                Text(
                    "This is a long secondary text for the current list item, " +
                            "displayed on two lines"
                )
            },
            singleLineSecondaryText = false,
            trailing = { Text("meta") }
        )
        Divider()
        ListItem(
            text = { Text("Three line list item") },
            overlineText = { Text("OVERLINE") },
            secondaryText = { Text("Secondary text") }
        )
        Divider()
        ListItem(
            text = { Text("Three line list item with 24x24 icon") },
            secondaryText = {
                Text(
                    "This is a long secondary text for the current list item " +
                            "displayed on two lines"
                )
            },
            singleLineSecondaryText = false,
            icon = { Image(icon24x24, colorFilter = ColorFilter.tint(contentColor())) }
        )
        Divider()
        ListItem(
            text = { Text("Three line list item with trailing icon") },
            secondaryText = {
                Text(
                    "This is a long secondary text for the current list" +
                            " item, displayed on two lines"
                )
            },
            singleLineSecondaryText = false,
            trailing = { Icon(vectorIcon) }
        )
        Divider()
        ListItem(
            text = { Text("Three line list item") },
            overlineText = { Text("OVERLINE") },
            secondaryText = { Text("Secondary text") },
            trailing = { Text("meta") }
        )
        Divider()
    }
}

// Demos for mixing RTL and LTR ListItems:

@Composable
fun OneLineRtlLtrListItems(icon24x24: ImageAsset, icon40x40: ImageAsset) {
    Column {
        ListItem(text = { Text("One line list item with no icon") })
        Divider()
        ListItem(
            text = { Text("פריט ברשימה אחת עם תמונה.") },
            icon = { Image(icon40x40, colorFilter = ColorFilter.tint(contentColor())) }
        )
        Divider()
        ListItem(
            text = { Text("One line list item with 24x24 icon") },
            icon = { Image(icon40x40, colorFilter = ColorFilter.tint(contentColor())) }
        )
        Divider()
        ListItem(
            text = { Text("عنصر قائمة واحد مع رمز زائدة") },
            trailing = { Image(icon24x24, colorFilter = ColorFilter.tint(contentColor())) }
        )
        Divider()
    }
}

@Composable
fun TwoLineRtlLtrListItems(icon40x40: ImageAsset) {
    Column {
        ListItem(
            text = { Text("Two line list item") },
            secondaryText = { Text("Secondary text") }
        )
        Divider()
        ListItem(
            text = { Text("بند قائمة من سطرين") },
            secondaryText = { Text("نص ثانوي") })
        Divider()
        ListItem(
            modifier = Modifier.clickable { },
            text = { Text("Clickable") },
            secondaryText = { Text("Short item") }
        )
        Divider()
        ListItem(
            text = { Text("عنصر قائمة مكون من سطرين مع رمز") },
            overlineText = { Text("فوق الخط") },
            icon = { Image(icon40x40, colorFilter = ColorFilter.tint(contentColor())) }
        )
        Divider()
        ListItem(
            text = { Text("Clickable two line item") },
            secondaryText = { Text("Secondary text") },
            icon = { Image(icon40x40, colorFilter = ColorFilter.tint(contentColor())) },
            trailing = {
                var checked by remember { mutableStateOf(false) }
                Checkbox(checked, onCheckedChange = { checked = !checked })
            },
            modifier = Modifier.clickable { }
        )
        Divider()
        ListItem(
            text = { Text("بندان قابلان للنقر") },
            secondaryText = { Text("نص ثانوي") },
            icon = { Image(icon40x40, colorFilter = ColorFilter.tint(contentColor())) },
            modifier = Modifier.clickable { }
        )
        Divider()
    }
}

@Composable
fun ThreeLineRtlLtrListItems(icon40x40: ImageAsset) {
    Column {
        ListItem(
            text = { Text("Three line list item") },
            overlineText = { Text("OVERLINE") },
            secondaryText = { Text("Secondary text") },
            trailing = { Text("meta") }
        )
        Divider()
        ListItem(
            text = { Text("ثلاثة عناصر قائمة بدون رمز") },
            secondaryText = {
                Text(
                    "هذا نص ثانوي طويل لعنصر القائمة الحالي ، معروض على سطرين. " +
                            "في لغات أخرى قد نحتاج إلى المزيد من النص"
                )
            },
            singleLineSecondaryText = false
        )
        Divider()
        ListItem(
            text = { Text("ثلاثة عناصر قائمة مع رمز") },
            overlineText = { Text("فوق الخط") },
            secondaryText = { Text("نص ثانوي") },
            icon = { Image(icon40x40, colorFilter = ColorFilter.tint(contentColor())) }
        )
        Divider()
    }
}
