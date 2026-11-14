package com.musicstar.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = Color.rgb(18, 18, 18)
        window.navigationBarColor = Color.rgb(18, 18, 18)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(24, 35, 24, 20)
        layout.setBackgroundColor(Color.rgb(18, 18, 18))

        val title = TextView(this)
        title.text = "🎵 MUSIC STAR"
        title.textSize = 28f
        title.setTextColor(Color.WHITE)
        title.gravity = Gravity.CENTER
        layout.addView(title)

        val subtitle = TextView(this)
        subtitle.text = "Your Music, Your World"
        subtitle.textSize = 15f
        subtitle.setTextColor(Color.LTGRAY)
        subtitle.gravity = Gravity.CENTER
        layout.addView(subtitle)

        val search = EditText(this)
        search.hint = "गाना या कलाकार खोजें..."
        search.setSingleLine(true)
        search.setTextColor(Color.WHITE)
        search.setHintTextColor(Color.GRAY)
        layout.addView(search)

        val categories = listOf(
            "🎧 Bollywood",
            "🎶 Bhojpuri",
            "🎤 Punjabi",
            "🎼 Hindi"
        )

        for (item in categories) {
            val button = Button(this)
            button.text = item
            layout.addView(button)
        }

        val message = TextView(this)
        message.text = "Welcome to Music Star!"
        message.textSize = 18f
        message.setTextColor(Color.WHITE)
        message.gravity = Gravity.CENTER
        layout.addView(message)

        setContentView(layout)
    }
}
