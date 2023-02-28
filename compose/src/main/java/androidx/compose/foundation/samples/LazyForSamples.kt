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

package androidx.compose.foundation.samples

import androidx.annotation.Sampled
import androidx.compose.foundation.Text
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.foundation.lazy.LazyRowFor
import androidx.compose.foundation.lazy.LazyRowForIndexed
import androidx.compose.runtime.Composable

@Sampled
@Composable
fun LazyColumnForSample() {
    val items = listOf("A", "B", "C")
    LazyColumnFor(items) {
        Text("Item is $it")
    }
}

@Sampled
@Composable
fun LazyRowForSample() {
    val items = listOf("A", "B", "C")
    LazyRowFor(items) {
        Text("Item is $it")
    }
}

@Sampled
@Composable
fun LazyColumnForIndexedSample() {
    val items = listOf("A", "B", "C")
    LazyColumnForIndexed(items) { index, item ->
        Text("Item at index $index is $item")
    }
}

@Sampled
@Composable
fun LazyRowForIndexedSample() {
    val items = listOf("A", "B", "C")
    LazyRowForIndexed(items) { index, item ->
        Text("Item at index $index is $item")
    }
}