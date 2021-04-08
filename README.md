# HighlightPro

**HighlightPro** is a highlight library for android and also it can be a simple popup window library for android.

### Features:

- One or more highlighted views can be displayed at once

- Custom exact position of the tip view by  horizontal constraint and vertical constraint

- Custom tip view display animation what you want **(TODO)**

- Custom highlight shape

- Chained code, simple to use

- Support  simple popup window

### Sample screenshots:

![guide_pro](https://github.com/hyy920109/HighLightPro/blob/master/screenshots/highlight_pro.gif)



![guide_pro_popup_window](https://github.com/hyy920109/HighLightPro/blob/master/screenshots/highlight_pro_popup.gif)

### Basic usage:

#### Gradle

Add dependence in your app's *build.gradle* file:

```groovy
dependencies {		
	implementation 'com.github.hyy920109:GuidePro:1.0.1'
}
```

#### Code Sample

You can start with any of **activity**   **fragment**  **viewGroup** like the below code:

```kotlin
/**
 * DecorView of [activity] treat as the rootView
 */
fun with(activity: Activity): HighlightPro {
    return HighlightPro(activity)
}

/**
 * DecorView of [fragment]'s [activity] treat as the rootView
 */
fun with(fragment: Fragment): HighlightPro {
    return HighlightPro(fragment)
}

/**
 * the [view] treat as rootView
 */
fun with(view: ViewGroup): HighlightPro {
    return HighlightPro(view)
}
```

A simple complete code:

```kotlin
HighlightPro.with(this)
            .setHighlightParameter {
                HighlightParameter.Builder()
                    .setHighlightViewId(R.id.btn_step_first)
                    .setTipsViewId(R.layout.guide_step_first)
                    .setHighlightShape(RectShape(4f.dp, 4f.dp, 6f))
                    .setHighlightHorizontalPadding(8f.dp)
                    .setConstraints(Constraints.StartToEndOfHighlight + Constraints.TopToTopOfHighlight)
                    .setMarginOffset(MarginOffset(start = 8.dp))
                    .build()
            }
            .setBackgroundColor("#80000000".toColorInt())
            .setOnShowCallback { index ->
                //do something
            }
            .setOnDismissCallback {
                //do something
            }
            .interceptBackPressed(true)//BackPressed will dismiss the HighligthPro default not intercepted
            .show()
```

Or sometimes you want to show more highlight views at once:

```kotlin
HighlightPro.with(this)
            .setHighlightParameters(
                HighlightParameter.Builder()
                    .setHighlightViewId(R.id.btn_step_first)
                    .setTipsViewId(R.layout.guide_step_first)
                    .setHighlightShape(RectShape(4f.dp, 4f.dp, 6f))
                    .setHighlightHorizontalPadding(8f.dp)
                    .setConstraints(Constraints.StartToEndOfHighlight + Constraints.TopToTopOfHighlight)
                    .setMarginOffset(MarginOffset(start = 8.dp))
                    .build()
                        +
                HighlightParameter.Builder()
                    .setHighlightViewId(R.id.btn_step_second)
                    .setTipsViewId(R.layout.guide_step_second)
                    .setHighlightShape(RectShape(4f.dp, 4f.dp, 6f))
                    .setHighlightHorizontalPadding(8f.dp)
                    .setConstraints(Constraints.StartToEndOfHighlight + Constraints.TopToTopOfHighlight)
                    .setMarginOffset(MarginOffset(start = 8.dp))
                    .build()
            )
            .setBackgroundColor("#80000000".toColorInt())
            .setOnShowCallback { index ->
                //do something
            }
            .setOnDismissCallback {
                //do something
            }
            .show()
```

Or sometimes we want display some steps highlight guide:

```kotlin
HighlightPro.with(this)
    .setHighlightParameter {
        HighlightParameter.Builder()
            .setHighlightViewId(R.id.btn_step_first)
            .setTipsViewId(R.layout.guide_step_first)
            .setHighlightShape(RectShape(4f.dp, 4f.dp, 6f))
            .setHighlightHorizontalPadding(8f.dp)
            .setConstraints(Constraints.StartToEndOfHighlight + Constraints.TopToTopOfHighlight)
            .setMarginOffset(MarginOffset(start = 8.dp))
            .build()
    }
    .setHighlightParameter {
        HighlightParameter.Builder()
            .setHighlightViewId(R.id.btn_step_second)
            .setTipsViewId(R.layout.guide_step_second)
            .setHighlightShape(CircleShape())
            .setHighlightHorizontalPadding(20f.dp)
            .setHighlightVerticalPadding(20f.dp)
            .setConstraints(Constraints.TopToBottomOfHighlight + Constraints.EndToEndOfHighlight)
            .setMarginOffset(MarginOffset(top = 8.dp))
            .build()
    }
    .setHighlightParameter {
        HighlightParameter.Builder()
            .setHighlightViewId(R.id.btn_step_third)
            .setTipsViewId(R.layout.guide_step_third)
            .setHighlightShape(OvalShape())
            .setHighlightHorizontalPadding(12f.dp)
            .setHighlightVerticalPadding(12f.dp)
            .setConstraints(Constraints.BottomToTopOfHighlight + Constraints.EndToEndOfHighlight)
            .setMarginOffset(MarginOffset(bottom = 6.dp))
            .build()
    }
    .setBackgroundColor("#80000000".toColorInt())
    .setOnShowCallback { index ->
        //do something
    }
    .setOnDismissCallback {
        //do something
    }
    .interceptBackPressed(true)
    .show()
```

Or sometimes we want disable highlight, it can also be a popup window:

```kotlin
HighlightPro.with(this)
    .setHighlightParameter {
        HighlightParameter.Builder()
            .setHighlightViewId(R.id.btn_tips_bottom)
            .setTipsViewId(R.layout.pop_tips_layout_bottom)
            .setConstraints(Constraints.TopToBottomOfHighlight + Constraints.EndToEndOfHighlight)
            .setMarginOffset(MarginOffset(end = (-2).dp))
            .build()
    }
    .enableHighlight(false)//no highlight now is a popup window
    .interceptBackPressed(true)//BackPressed will dismiss the HighligthPro default not intercepted
    .show()
```

#### API doc

##### About  [HighlightParameter.Builder](https://github.com/hyy920109/HighLightPro/blob/master/highlight_pro/src/main/java/com/hyy/highlightpro/parameter/HighlightParameter.kt)

| Method                        | Description                                                  |
| ----------------------------- | ------------------------------------------------------------ |
| setHighlightViewId            | Set id of highlight view                                     |
| setHighlightView              | Set the highlight view                                       |
| setTipsViewId                 | Set id of tips view                                          |
| setTipsView                   | Set tips view                                                |
| setHighlightShape             | Set shape of highlight                                       |
| setHighlightVerticalPadding   | Set v-padding of highlight rectangle                         |
| setHighlightHorizontalPadding | Set v-padding of highlight rectangle                         |
| setConstraints                | Set constraints of tips view                                 |
| setMarginOffset               | Set tips view's margin relative highlight rectangle          |
| build                         | To create a [HighlightParameter](https://github.com/hyy920109/HighLightPro/blob/master/highlight_pro/src/main/java/com/hyy/highlightpro/parameter/HighlightParameter.kt)  object |

**Note**

- setHighlightViewId & setHighlightView
- setTipsViewId & setTipsView

For the above two methods, you only need one. If you don't use any one,  this lib will display UI unexpected.

##### About [HighlightShape](https://github.com/hyy920109/HighLightPro/blob/master/highlight_pro/src/main/java/com/hyy/highlightpro/shape/HighlightShape.kt)

| HighlightShape                                               | Description                 |
| ------------------------------------------------------------ | --------------------------- |
| [RectShape](https://github.com/hyy920109/HighLightPro/blob/master/highlight_pro/src/main/java/com/hyy/highlightpro/shape/RectShape.kt) | A rectangle highlight shape |
| [CircleShape](https://github.com/hyy920109/HighLightPro/blob/master/highlight_pro/src/main/java/com/hyy/highlightpro/shape/CircleShape.kt) | A circle highlight shape    |
| [OvalShape](https://github.com/hyy920109/HighLightPro/blob/master/highlight_pro/src/main/java/com/hyy/highlightpro/shape/OvalShape.kt) | An oval highlight shape     |

**Note**

Any shape base on highlight view's rect on screen, we can **setHighlightVerticalPadding** or **setHighlightHorizontalPadding** to expand highlight area.

##### About  [Constraints](https://github.com/hyy920109/HighLightPro/blob/master/highlight_pro/src/main/java/com/hyy/highlightpro/parameter/Constraints.kt)

It is the core class which determine the position of tips view. Like Android's ConstraintLayout our all constriants depend on  highlight view.

| Vertical Constraints      | Description                                                 |
| ------------------------- | ----------------------------------------------------------- |
| TopToTopOfHighlight       | Align tips view **top** to **top** of highlight rect.       |
| TopToBottomOfHighlight    | Align tips view **top** to **bottom** of highlight rect.    |
| BottomToBottomOfHighlight | Align tips view **bottom** to **bottom** of highlight rect. |
| BottomToTopOfHighlight    | Align tips view **bottom** to **top** of highlight rect.    |

| Horizontal Constraint   | Description                                               |
| ----------------------- | --------------------------------------------------------- |
| StartToStartOfHighlight | Align tips view **start** to **start** of highlight rect. |
| StartToEndOfHighlight   | Align tips view **start** to **end** of highlight rect.   |
| EndToEndOfHighlight     | Align tips view **end** to **end** of highlight rect.     |
| EndToStartOfHighlight   | Align tips view **end** to **start** of highlight rect.   |

**Note**

Usually we should set a list of constraints that contains one v-constraint and one h-constraint. And we can use **plus** operator to create a list of constraints:

```kotlin
Constraints.TopToBottomOfHighlight + Constraints.EndToEndOfHighlight
```

Of course, we can set margin offsets  to adjust position by :

```kotlin
setMarginOffset(MarginOffset(start = 8.dp))
```

##### End

Above we introduce all usages of [HighlightPro](https://github.com/hyy920109/HighLightPro) , if it helps you ,  star please. Or if you have some good advice, pull request is open to you. At the end of  introduce , thanks for [Lighter](https://github.com/samlss/Lighter) ‘s author, some of  thinking from it.

