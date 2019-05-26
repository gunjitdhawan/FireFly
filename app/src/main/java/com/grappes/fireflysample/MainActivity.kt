package com.grappes.fireflysample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.grappes.firefly.Shape
import com.grappes.firefly.glow
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv.glow(Shape.RECTANGLE)
        img.glow(Shape.CIRCLE)



    }
}
