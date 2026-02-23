package com.syrja.node

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val text = TextView(this)
        text.text = "Syrja Node: Engine Initializing..."
        text.textSize = 20f
        text.setPadding(60, 60, 60, 60)
        setContentView(text)

        // Launch the Immortal Engine
        val serviceIntent = Intent(this, NodeService::class.java)
        startForegroundService(serviceIntent)
    }
}
