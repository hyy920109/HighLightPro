### HighlightPro

**HighlightPro** is a highlight library for android and also it can be a simple popup window library for android.

##### Features:

- One or more highlighted views can be displayed at once

- Custom exact position of the tip view by  horizontal constraint and vertical constraint

- Custom tip view display animation what you want **(TODO)**

- Custom highlight shape

- Chained code, simple to use

- Support  simple popup window

##### Sample screenshots:

![guide_pro](https://github.com/hyy920109/HighLightPro/master/screenshots/highlight_pro.gif)



![guide_pro_popup_window](https://github.com/hyy920109/HighLightPro/master/screenshots/highlight_pro_popup.gif)

##### Basic usage:

**Gradle**

Add dependence in your app's *build.gradle* file:

```groovy
dependencies {		
	implementation 'com.github.hyy920109:GuidePro:1.0.1'
}
```

##### Code Sample

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
                    .setHighLightViewId(R.id.btn_step_first)
                    .setTipsViewId(R.layout.guide_step_first)
                    .setHighLightShape(RectShape(4f.dp, 4f.dp, 6f))
                    .setHighLightHorizontalPadding(8f.dp)
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
            .interceptBackPressed(true)//BackPressed will dismiss the HighligthPro
            .show()
```

Or sometimes you want to show more highlight views at once:

```kotlin
HighlightPro.with(this)
            .setHighlightParameters(
                HighlightParameter.Builder()
                    .setHighLightViewId(R.id.btn_step_first)
                    .setTipsViewId(R.layout.guide_step_first)
                    .setHighLightShape(RectShape(4f.dp, 4f.dp, 6f))
                    .setHighLightHorizontalPadding(8f.dp)
                    .setConstraints(Constraints.StartToEndOfHighlight + Constraints.TopToTopOfHighlight)
                    .setMarginOffset(MarginOffset(start = 8.dp))
                    .build()
                        +
                HighlightParameter.Builder()
                    .setHighLightViewId(R.id.btn_step_second)
                    .setTipsViewId(R.layout.guide_step_second)
                    .setHighLightShape(RectShape(4f.dp, 4f.dp, 6f))
                    .setHighLightHorizontalPadding(8f.dp)
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



