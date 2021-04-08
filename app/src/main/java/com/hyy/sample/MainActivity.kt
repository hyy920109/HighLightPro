package com.hyy.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    //①不管是传activity对象还是传view大体思路是 先拿到一个rootView（viewGroup）对象
    //②将我们预先准备好的FrameLayout放到rootView上面
    //③通过传进来的布局id、targetId、位置等 将布局添加到FrameLayout
    //④通过找到对应targetId View在屏幕中的位置以及其矩形区域来绘制透明效果
    //⑤将根据位置参数将tip view放置到对应位置通过设置margin来达到位置的摆放
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}