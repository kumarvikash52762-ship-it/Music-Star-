package com.musicstar.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import android.graphics.Typeface

class MainActivity : Activity() {

    private val bg = Color.rgb(15, 15, 25)
    private val purple = Color.rgb(130, 75, 255)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = bg
        window.navigationBarColor = bg

        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.setPadding(20, 25, 20, 15)
        root.setBackgroundColor(bg)

        val scroll = ScrollView(this)
        val content = LinearLayout(this)
        content.orientation = LinearLayout.VERTICAL

        val title = TextView(this)
        title.text = "🎵 MUSIC STAR"
        title.textSize = 30f
        title.typeface = Typeface.DEFAULT_BOLD
        title.gravity = Gravity.CENTER
        title.setTextColor(Color.WHITE)
        content.addView(title)

        val subtitle = TextView(this)
        subtitle.text = "Your Music, Your World"
        subtitle.gravity = Gravity.CENTER
        subtitle.textSize = 14f
        subtitle.setTextColor(Color.LTGRAY)
        content.addView(subtitle)

        val search = EditText(this)
        search.hint = "🔍 गाना, कलाकार या मूवी खोजें"
        search.setSingleLine(true)
        search.setTextColor(Color.WHITE)
        search.setHintTextColor(Color.LTGRAY)
        search.setPadding(15, 12, 15, 12)
        search.setBackgroundColor(Color.rgb(40, 40, 55))
        content.addView(search)

        addHeading(content, "🔥 Explore Music")

        addCategory(content, "🎧 Bollywood Songs")
        addCategory(content, "🎶 Bhojpuri Songs")
        addCategory(content, "🎤 Punjabi Songs")
        addCategory(content, "🎼 Hindi Songs")

        addHeading(content, "📺 Video Songs")
        addCategory(content, "▶ Watch Music Videos")

        addHeading(content, "🎬 Movies")
        addCategory(content, "🍿 Free & Legal Movies")

        addHeading(content, "🎵 Music Player")

        val now = TextView(this)
        now.text = "Select a song to start listening"
        now.textSize = 16f
        now.gravity = Gravity.CENTER
        now.setTextColor(Color.WHITE)
        now.setPadding(10, 20, 10, 20)
        content.addView(now)

        val controls = LinearLayout(this)
        controls.gravity = Gravity.CENTER

        for (label in listOf("⏮", "▶", "⏭")) {
            val button = Button(this)
            button.text = label
            controls.addView(button)
        }

        content.addView(controls)

        val footer = TextView(this)
        footer.text = "MUSIC STAR • Free Music Experience"
        footer.gravity = Gravity.CENTER
        footer.setTextColor(Color.GRAY)
        footer.setPadding(10, 30, 10, 10)
        content.addView(footer)

        scroll.addView(content)
        root.addView(scroll)
        setContentView(root)
    }

    private fun addHeading(
        layout: LinearLayout,
        text: String
    ) {
        val heading = TextView(this)
        heading.text = text
        heading.textSize = 21f
        heading.typeface = Typeface.DEFAULT_BOLD
        heading.setTextColor(Color.WHITE)
        heading.setPadding(0, 25, 0, 12)
        layout.addView(heading)
    }

    private fun addCategory(
        layout: LinearLayout,
        text: String
    ) {
        val button = Button(this)
        button.text = text
        button.textSize = 16f
        button.setTextColor(Color.WHITE)
        button.setBackgroundColor(purple)

        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.bottomMargin = 8
        layout.addView(button, params)
    }

}
