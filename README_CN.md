# HighlightPro

**HighlightPro** 是Android的一个高亮引导库，同时它还可以是一个简单的popup window库

### 功能:

- 可一次显示一个或多个高亮view
- 通过水平约束和竖直约束来准确定位提示view
- 自定义提示view的出现动画
- 自定义高亮形状和大小
- 自定义高亮形状的paint
- 链式调用，使用简单
- 支持简单的pop window

### 效果图:

![guide_pro](https://github.com/hyy920109/HighLightPro/blob/master/screenshots/highlight_pro_cn.gif)



![guide_pro_popup_window](https://github.com/hyy920109/HighLightPro/blob/master/screenshots/highlight_pro_popup_cn.gif)




![highlight_recycler_view](https://github.com/hyy920109/HighLightPro/blob/master/screenshots/highlight_recycler_view_cn.gif)

### 用法:

#### Gradle

在项目级的build.gradle文件种添加maven存储仓库Jitpack：

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

添加依赖到你的app的build.gradle 文件中：

```groovy
dependencies {		
	implementation 'com.github.hyy920109:highlight-pro:1.3.0'
}
```

#### 代码

我们可以通过Activity Fragment ViewGroup其中一个拿到HighlightPro对象：

```kotlin
/**
 * 会使用activity.window.decorView 作为父view 全屏显示
 */
fun with(activity: Activity): HighlightPro {
    return HighlightPro(activity)
}

/**
 * 会使用会使用fragment依赖的activity的activity.window.decorView 作为父view 全屏显示
 */
fun with(fragment: Fragment): HighlightPro {
    return HighlightPro(fragment)
}


/**
 * 通过viewGroup来构建HighlightPro，这个ViewGroup一定要是FrameLayout或者FrameLayout的子类
 */
fun with(container: FrameLayout): HighlightPro {
    return HighlightPro(container)
}
```

简单的完整链式调用

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
            .show()
```

如果你想一次展示多个高亮view可以看如下代码（区别是传入的是一个List<HighlightParameter>）：

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

如果我们连环展示高亮view(比如第一步、第二步。。。)，可以采用多次调用setHighlightParameter, 代码如下：

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

如果我们向现实pop window, 可以通过enableHighlight来控制，传入false 就代表了不会高亮target view:

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
    .enableHighlight(false)//禁用highlight
    .interceptBackPressed(true)//拦截返回键，返回键会导致 popwindow消失
    .show()
```

#### API 文档

##### 关于  [HighlightParameter.Builder](https://github.com/hyy920109/HighLightPro/blob/master/highlight_pro/src/main/java/com/hyy/highlightpro/parameter/HighlightParameter.kt)

| 方法名                        | 方法描述                                                     |
| ----------------------------- | ------------------------------------------------------------ |
| setHighlightViewId            | 设置高亮view的id                                             |
| setHighlightView              | 设置高亮view                                                 |
| setTipsViewId                 | 设置提示view的id                                             |
| setTipsView                   | 设置提示view                                                 |
| setHighlightShape             | 设置高亮形状                                                 |
| setHighlightVerticalPadding   | 设置高亮区域的竖直padding                                    |
| setHighlightHorizontalPadding | 设置高亮区域的水平padding                                    |
| setConstraints                | 设置提示view的相关约束                                       |
| setMarginOffset               | 设置提示view的偏移量                                         |
| build                         | 返回一个 [HighlightParameter](https://github.com/hyy920109/HighLightPro/blob/master/highlight_pro/src/main/java/com/hyy/highlightpro/parameter/HighlightParameter.kt) 对象 |

**注意**

- setHighlightViewId & setHighlightView
- setTipsViewId & setTipsView

对于上面两组方法，你只需要使用每组中其中一个方法。如果都没使用，UI 表现会不正常

##### 关于 [HighlightShape](https://github.com/hyy920109/HighLightPro/blob/master/highlight_pro/src/main/java/com/hyy/highlightpro/shape/HighlightShape.kt)

| HighlightShape                                               | 方法描述           |
| ------------------------------------------------------------ | ------------------ |
| [RectShape](https://github.com/hyy920109/HighLightPro/blob/master/highlight_pro/src/main/java/com/hyy/highlightpro/shape/RectShape.kt) | 边缘模糊的矩形图形 |
| [CircleShape](https://github.com/hyy920109/HighLightPro/blob/master/highlight_pro/src/main/java/com/hyy/highlightpro/shape/CircleShape.kt) | 边缘模糊的圆形     |
| [OvalShape](https://github.com/hyy920109/HighLightPro/blob/master/highlight_pro/src/main/java/com/hyy/highlightpro/shape/OvalShape.kt) | 边缘模糊的椭圆形   |

**注意**

任何形状都是基于高亮view在屏幕中的矩形区域，我们可以通过**setHighlightVerticalPadding** 或 **setHighlightHorizontalPadding**来扩大高亮区域

##### 关于 [Constraints](https://github.com/hyy920109/HighLightPro/blob/master/highlight_pro/src/main/java/com/hyy/highlightpro/parameter/Constraints.kt)

这个类是决定提示view位置的核心类，类似于Android自带的约束布局，目前我们所有的约束均依赖于高亮view

| Vertical Constraints      | 属性描述                         |
| ------------------------- | -------------------------------- |
| TopToTopOfHighlight       | 将提示view顶部和高亮矩形顶部对齐 |
| TopToBottomOfHighlight    | 将提示view顶部和高亮矩形底部对齐 |
| BottomToBottomOfHighlight | 将提示view底部和高亮矩形底部对齐 |
| BottomToTopOfHighlight    | 将提示view底部和高亮矩形顶部对齐 |

| Horizontal Constraint   | 属性描述                         |
| ----------------------- | -------------------------------- |
| StartToStartOfHighlight | 将提示view左侧和高亮矩形左侧对齐 |
| StartToEndOfHighlight   | 将提示view左侧和高亮矩形右侧对齐 |
| EndToEndOfHighlight     | 将提示view右侧和高亮矩形右侧对齐 |
| EndToStartOfHighlight   | 将提示view右侧和高亮矩形左侧对齐 |

**注意**

通常我们就设置两个约束：一个竖直约束，一个水平约束，而且我们可以通过运算符重载+来构建约束集合：

```kotlin
Constraints.TopToBottomOfHighlight + Constraints.EndToEndOfHighlight
```

当然我们也可以设置偏移量来调整提示view的位置：

```kotlin
setMarginOffset(MarginOffset(start = 8.dp))
```

### 最后

上面我们基本把[HighlightPro](https://github.com/hyy920109/HighLightPro)的用法介绍完了，欢迎大家使用，如果它对您有帮助，给个star呗。如果你有更好的建议或思路，欢迎提交pull request。
