# Checkbox

!!! info
    This is the API of version 1.0.0-alpha02. Newer versions may have a different one

<p align="left">
  <img src ="../../images/material/checkbox/CheckboxDemo.png" height=100 width=300 />
</p>

```kotlin
@Composable
fun CheckBoxDemo() {
        val checkedState = remember { mutableStateOf(true) }
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it }
        )
}
```
<hr>
## See also:
* [Compose Academy/Checkbox](https://compose.academy/academy/material/checkbox/)
