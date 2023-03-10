/*
 * Copyright 2018 The Android Open Source Project
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

package androidx.compose.foundation.text.demos

import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.annotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.text.samples.BaselineShiftSample
import androidx.compose.ui.text.samples.FontFamilyCursiveSample
import androidx.compose.ui.text.samples.FontFamilyMonospaceSample
import androidx.compose.ui.text.samples.FontFamilySansSerifSample
import androidx.compose.ui.text.samples.FontFamilySerifSample
import androidx.compose.ui.text.samples.ParagraphStyleAnnotatedStringsSample
import androidx.compose.ui.text.samples.ParagraphStyleSample
import androidx.compose.ui.text.samples.TextDecorationCombinedSample
import androidx.compose.ui.text.samples.TextDecorationLineThroughSample
import androidx.compose.ui.text.samples.TextDecorationUnderlineSample
import androidx.compose.ui.text.samples.TextStyleSample
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

const val displayText = "Text Demo"
const val displayTextChinese = "????????????"
const val displayTextArabic = "?????? ????????"
const val displayTextHindi = "????????? ????????????"
val fontSize4 = 16.sp
val fontSize6 = 20.sp
val fontSize8 = 25.sp
val fontSize10 = 30.sp

@Composable
fun TextDemo() {
    ScrollableColumn {
        TagLine(tag = "color, fontSize, fontWeight and fontStyle")
        TextDemoBasic()
        TagLine(
            tag = "color, fontSize, fontWeight, fontFamily, fontStyle, letterSpacing, " +
                    "background, decoration"
        )
        TextDemoComplexStyling()
        TagLine(tag = "Chinese, Arabic, and Hindi")
        TextDemoLanguage()
        TagLine(tag = "FontFamily generic names")
        TextDemoFontFamily()
        TagLine(tag = "FontFamily default values")
        TextDemoFontFamilyDefaultValues()
        TagLine(tag = "decoration, decorationColor and decorationStyle")
        TextDemoTextDecoration()
        TagLine(tag = "letterSpacing")
        TextDemoLetterSpacing()
        TagLine(tag = "baselineShift")
        TextDemoBaselineShift()
        TagLine(tag = "lineHeight")
        TextDemoHeight()
        TagLine(tag = "background")
        TextDemoBackground()
        TagLine(tag = "Locale: Japanese, Simplified and Traditional Chinese")
        TextDemoLocale()
        TagLine(tag = "textAlign and textDirection")
        TextDemoTextAlign()
        TagLine(tag = "softWrap: on and off")
        TextDemoSoftWrap()
        TagLine(tag = "shadow")
        TextDemoShadowEffect()
        TagLine(tag = "fontSizeScale")
        TextDemoFontSizeScale()
        TagLine(tag = "complex paragraph styling")
        TextDemoParagraphStyling()
    }
}

@Composable
fun TagLine(tag: String) {
    Text(
        style = TextStyle(fontSize = fontSize8),
        text = annotatedString {
            append("\n")
            withStyle(
                style = SpanStyle(
                    color = Color(0xFFAAAAAA),
                    fontSize = fontSize6
                )
            ) {
                append(tag)
            }
        }
    )
}

@Composable
fun SecondTagLine(tag: String) {
    Text(
        text = annotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color(0xFFAAAAAA),
                    fontSize = fontSize4
                )
            ) {
                append(tag)
            }
        }
    )
}

@Composable
fun TextDemoBasic() {
    // This group of text composables show different color, fontSize, fontWeight and fontStyle in
    // English.
    Text(text = annotatedString {
        withStyle(
            SpanStyle(
                color = Color(0xFFFF0000),
                fontSize = fontSize6,
                fontWeight = FontWeight.W200,
                fontStyle = FontStyle.Italic
            )
        ) {
            append("$displayText   ")
        }

        withStyle(
            SpanStyle(
                color = Color(0xFF00FF00),
                fontSize = fontSize8,
                fontWeight = FontWeight.W500,
                fontStyle = FontStyle.Normal
            )
        ) {
            append("$displayText   ")
        }

        withStyle(
            SpanStyle(
                color = Color(0xFF0000FF),
                fontSize = fontSize10,
                fontWeight = FontWeight.W800,
                fontStyle = FontStyle.Normal
            )
        ) {
            append(displayText)
        }
    })
}

@Composable
fun TextDemoComplexStyling() {
    TextStyleSample()
}

@Composable
fun TextDemoLanguage() {
    // This group of text composables show different color, fontSize, fontWeight and fontStyle in
    // Chinese, Arabic, and Hindi.
    Text(text = annotatedString {
        withStyle(
            style = SpanStyle(
                color = Color(0xFFFF0000),
                fontSize = fontSize6,
                fontWeight = FontWeight.W200,
                fontStyle = FontStyle.Italic
            )
        ) {
            append("$displayTextChinese   ")
        }

        withStyle(
            style = SpanStyle(
                color = Color(0xFF00FF00),
                fontSize = fontSize8,
                fontWeight = FontWeight.W500,
                fontStyle = FontStyle.Normal
            )
        ) {
            append("$displayTextArabic   ")
        }

        withStyle(
            style = SpanStyle(
                color = Color(0xFF0000FF),
                fontSize = fontSize10,
                fontWeight = FontWeight.W800,
                fontStyle = FontStyle.Normal
            )
        ) {
            append(displayTextHindi)
        }
    })
}

@Composable
fun TextDemoFontFamily() {
    // This group of text composables show different fontFamilies in English.
    Text(annotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = fontSize8,
                fontFamily = FontFamily.SansSerif
            )
        ) {
            append("$displayText sans-serif\n")
        }

        withStyle(
            style = SpanStyle(
                fontSize = fontSize8,
                fontFamily = FontFamily.Serif
            )
        ) {
            append("$displayText serif\n")
        }

        withStyle(
            style = SpanStyle(
                fontSize = fontSize8,
                fontFamily = FontFamily.Monospace
            )
        ) {
            append("$displayText monospace")
        }
    })
}

@Composable
fun TextDemoFontFamilyDefaultValues() {
    // This group of text composables show the default font families in English.
    FontFamilySerifSample()
    FontFamilySansSerifSample()
    FontFamilyMonospaceSample()
    FontFamilyCursiveSample()
}

@Composable
fun TextDemoTextDecoration() {
    // This group of text composables show different decoration, decorationColor and decorationStyle.
    TextDecorationLineThroughSample()
    TextDecorationUnderlineSample()
    TextDecorationCombinedSample()
}

@Composable
fun TextDemoLetterSpacing() {
    // This group of text composables show different letterSpacing.
    Text(text = annotatedString {
        withStyle(style = SpanStyle(fontSize = fontSize8)) {
            append("$displayText   ")
        }
        withStyle(
            style = SpanStyle(
                fontSize = fontSize8,
                letterSpacing = 0.5.em
            )
        ) {
            append(displayText)
        }
    })
}

@Composable
fun TextDemoBaselineShift() {
    BaselineShiftSample()
}

@Composable
fun TextDemoHeight() {
    // This group of text composables show different height.
    Row(Modifier.fillMaxWidth()) {
        Text(
            text = "$displayText\n$displayText   ",
            style = TextStyle(fontSize = fontSize8)
        )
        Text(
            text = "$displayText\n$displayText   ",
            style = TextStyle(fontSize = fontSize8, lineHeight = 50.sp)
        )
    }
}

@Composable
fun TextDemoBackground() {
    // This group of text composables show different background.
    Text(
        text = annotatedString {
            withStyle(style = SpanStyle(background = Color(0xFFFF0000))) {
                append("$displayText   ")
            }

            withStyle(style = SpanStyle(background = Color(0xFF00FF00))) {
                append("$displayText   ")
            }

            withStyle(style = SpanStyle(background = Color(0xFF0000FF))) {
                append(displayText)
            }
        },
        style = TextStyle(fontSize = fontSize8)
    )
}

@Composable
fun TextDemoLocale() {
    // This group of text composables show different Locales of the same Unicode codepoint.
    val text = "\u82B1"
    Text(
        text = annotatedString {
            withStyle(style = SpanStyle(localeList = LocaleList("ja-JP"))) {
                append("$text   ")
            }

            withStyle(style = SpanStyle(localeList = LocaleList("zh-CN"))) {
                append("$text   ")
            }

            withStyle(style = SpanStyle(localeList = LocaleList("zh-TW"))) {
                append(text)
            }
        },
        style = TextStyle(fontSize = fontSize8)
    )
}

@Composable
fun TextDemoTextAlign() {
    // This group of text composables show different TextAligns: LEFT, RIGHT, CENTER, JUSTIFY, START for
    // LTR and RTL, END for LTR and RTL.
    var text = ""
    for (i in 1..10) {
        text = "$text$displayText "
    }
    Column(Modifier.fillMaxHeight()) {
        SecondTagLine(tag = "textAlign = TextAlign.Left")
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = displayText,
            style = TextStyle(fontSize = fontSize8, textAlign = TextAlign.Left)
        )

        SecondTagLine(tag = "textAlign = TextAlign.Right")
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = displayText,
            style = TextStyle(fontSize = fontSize8, textAlign = TextAlign.Right)
        )

        SecondTagLine(tag = "textAlign = TextAlign.Center")
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = displayText,
            style = TextStyle(fontSize = fontSize8, textAlign = TextAlign.Center)
        )

        SecondTagLine(tag = "textAlign = default and TextAlign.Justify")
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            style = TextStyle(
                fontSize = fontSize8,
                color = Color(0xFFFF0000)
            )
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            style = TextStyle(
                fontSize = fontSize8,
                color = Color(0xFF0000FF),
                textAlign = TextAlign.Justify
            )
        )

        SecondTagLine(tag = "textAlign = TextAlign.Start for Ltr")
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = displayText,
            style = TextStyle(fontSize = fontSize8, textAlign = TextAlign.Start)
        )
        SecondTagLine(tag = "textAlign = TextAlign.Start for Rtl")
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = displayTextArabic,
            style = TextStyle(fontSize = fontSize8, textAlign = TextAlign.Start)
        )
        SecondTagLine(tag = "textAlign = TextAlign.End for Ltr")
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = displayText,
            style = TextStyle(fontSize = fontSize8, textAlign = TextAlign.End)
        )
        SecondTagLine(tag = "textAlign = TextAlign.End for Rtl")
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = displayTextArabic,
            style = TextStyle(fontSize = fontSize8, textAlign = TextAlign.End)
        )
    }
}

@Composable
fun TextDemoSoftWrap() {
    // This group of text composables show difference between softWrap is true and false.
    var text = ""
    for (i in 1..10) {
        text = "$text$displayText"
    }
    val textStyle = TextStyle(fontSize = fontSize8, color = Color(0xFFFF0000))

    Column(Modifier.fillMaxHeight()) {
        Text(text = text, style = textStyle)
        Text(text = text, style = textStyle, softWrap = false)
    }
}

@Composable
fun TextDemoShadowEffect() {
    val shadow = Shadow(
        Color(0xFFE0A0A0),
        Offset(5f, 5f),
        blurRadius = 5.0f
    )
    Text(
        style = TextStyle(fontSize = fontSize8),
        text = annotatedString {
            append("text with ")
            withStyle(style = SpanStyle(shadow = shadow)) {
                append("shadow!")
            }
        }
    )
}

@Composable
fun TextDemoFontSizeScale() {
    Text(
        style = TextStyle(fontSize = fontSize8),
        text = annotatedString {
            for (i in 4..12 step 4) {
                val scale = i * 0.1f
                withStyle(style = SpanStyle(fontSize = scale.em)) {
                    append("fontSizeScale=$scale\n")
                }
            }
        }
    )
}

@Composable
fun TextDemoParagraphStyling() {
    ParagraphStyleSample()
    ParagraphStyleAnnotatedStringsSample()
}
