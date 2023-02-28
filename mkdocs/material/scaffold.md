# Scaffold

!!! info
    This is the API of version 1.0.0-alpha02. Newer versions may have a different one
    
A Scaffold is a layout which implements the basic material design layout structure. You can add things like a TopBar, BottomBar, FAB or a Drawer.

```kotlin
@Composable
fun ScaffoldDemo() {
    val materialBlue700= Color(0xFF1976D2)
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Open))
        Scaffold(
                scaffoldState = scaffoldState,
                topBar = { TopAppBar(title = {Text("TopAppBar")},backgroundColor = materialBlue700)  },
                floatingActionButtonPosition = FabPosition.End,
                floatingActionButton = { FloatingActionButton(onClick = {}){
                    Text("X")
                } },
                drawerContent = { Text(text = "drawerContent") },
                bodyContent = { Text("BodyContent") },
                bottomBar = { BottomAppBar(backgroundColor = materialBlue700) { Text("BottomAppBar") } }
        )
}
```

<p align="left">
  <img src ="../../images/material/scaffold/scaffold.png"  height=100 width=300  />
  <img src ="../../images/material/scaffold/scaffoldwithdrawer.png"  height=100 width=300/>

</p>


## scaffoldState
With the scaffoldState you can set the opening state of the drawer(DrawerState.Opened or DrawerState.Closed)


## topBar

<p align="left">
  <img src ="../../images/material/scaffold/topappbar.png"  />
</p>

Here you can set the part of your layout that should be displayed on top of the screen. You can use it for things like a toolbar. You can set any Composable, but **TopAppBar** is already made for this usecase.

## floatingActionButton
<p align="left">
  <img src ="../../images/material/scaffold/floatingactionbutton.png"  />
</p>
Here you can add FloatingActionButton. You can set any Composable, but **FloatingActionButton** is already made for this usecase

## floatingActionButtonPosition
When you have added a FAB, you can use this specify the position of it. The default position is at the end of your layout.
 
## drawerContent
Here you can set the content of your drawer.

## bodyContent
This is the primary content of the scaffold. You can add any Composable here.

## bottomBar

<p align="left">
  <img src ="../../images/material/scaffold/bottomappbar.png"  />
</p>

Here you can set the part of your layout is on bottom of the screen. You can set any Composable, but **BottomAppBar** is already made for this usecase.