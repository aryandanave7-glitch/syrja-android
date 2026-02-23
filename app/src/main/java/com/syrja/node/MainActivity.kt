package com.syrja.node

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(60, 60, 60, 60)

        val title = TextView(this)
        title.text = "Syrja Node"
        title.textSize = 28f

        val status = TextView(this)
        status.text = "Engine Status: Immortal\nTap 'Connect' to link with Desktop."
        status.setPadding(0, 20, 0, 40)

        val btn = Button(this)
        btn.text = "Connect to Desktop"
        btn.setOnClickListener {
            val serviceIntent = Intent(this, NodeService::class.java)
            startForegroundService(serviceIntent)
        }

        layout.addView(title)
        layout.addView(status)
        layout.addView(btn)

        setContentView(layout)
    }
}
