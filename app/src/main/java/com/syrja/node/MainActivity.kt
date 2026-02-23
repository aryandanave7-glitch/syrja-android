package com.syrja.node

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val text = TextView(this)
        text.text = "Syrja Node: Cloud Factory Active!"
        text.textSize = 24f
        text.setPadding(50, 50, 50, 50)

        setContentView(text)
    }
}
