# How to use Compose in a ViewGroup

!!! info
    This is the API of version dev16. Newer versions may have a different one

Compose has a **setContent()**-extension function on ViewGroup which can be used, to use Compose inside a ViewGroup.
This example will show you, how you can use Compose inside a FrameLayout. It should also work in other layouts like LinearLayout. As long as it's a layout that extends the **ViewGroup** class.

```kotlin
//Extension function from Compose
fun ViewGroup.setContent(
    recomposer: Recomposer,
    parentComposition: CompositionReference? = null,
    content: @Composable () -> Unit
): Composition {
    FrameManager.ensureStarted()
    val composeView =
        if (childCount > 0) {
            getChildAt(0) as? AndroidOwner
        } else {
            removeAllViews(); null
        } ?: AndroidOwner(context).also { addView(it.view, DefaultLayoutParams) }
    return doSetContent(composeView, recomposer, parentComposition, content)
}
```

## Create a custom FrameLayout
Create a custom FrameLayout, then you can use the **setContent()** for example inside **init()**. 
Inside **setContent()** you can then add your Compose code.
```kotlin
class ComposeFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        setContent(Recomposer.current(),null){
            Button(onClick = {}) {
                Text("ComposeButton")
            }
        }
    }
}
```

## Add it to your layout file
Just add your FrameLayout like any other layout.
```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/container"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <ComposeFrameLayout
        android:id="@+id/composeFrame"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="horizontal" />

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="WidgetButton"
        />
</LinearLayout>
```

<p align="center">
  <img src ="../../images/cookbook/viewgroup/viewgroupExample.png"  height=100 width=200  />
</p>