
package com.musicstar.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import android.content.Intent
import android.net.Uri

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
        search.hint = "🔍 गाना या कलाकार खोजें"
        search.setSingleLine(true)
        search.setTextColor(Color.WHITE)
        search.setHintTextColor(Color.LTGRAY)
        search.setPadding(15, 12, 15, 12)
        search.setBackgroundColor(Color.rgb(40, 40, 55))
        content.addView(search)

        addHeading(content, "🔥 Explore Music")

        addCategory(content, "🎧 Bollywood Songs") {
            openSearch("Bollywood songs")
        }

        addCategory(content, "🎶 Bhojpuri Songs") {
            openSearch("Bhojpuri songs")
        }

        addCategory(content, "🎤 Punjabi Songs") {
            openSearch("Punjabi songs")
        }

        addCategory(content, "🎼 Hindi Songs") {
            openSearch("Hindi songs")
        }

        addCategory(content, "🔍 Search Your Song") {
            val query = search.text.toString().trim()
            if (query.isNotEmpty()) openSearch(query)
            else Toast.makeText(this, "पहले गाने का नाम लिखें", Toast.LENGTH_SHORT).show()
        }

        addHeading(content, "📺 Video Songs")

        addCategory(content, "▶ Watch Music Videos") {
            openSearch("Music videos")
        }

        addHeading(content, "🎬 Movies")

        addCategory(content, "🍿 Free & Legal Movies") {
            openSearch("Public domain movies")
        }

        addHeading(content, "🎵 Music Player")

        val now = TextView(this)
        now.text = "Select a song to start listening"
        now.textSize = 16f
        now.gravity = Gravity.CENTER
        now.setTextColor(Color.WHITE)
        now.setPadding(10, 20, 10, 20)
        content.addView(now)

        val play = Button(this)
        play.text = "▶ Play Music"
        play.setOnClickListener {
            openSearch("Hindi music")
        }
        content.addView(play)

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

    private fun openSearch(query: String) {
        val url = "https://www.youtube.com/results?search_query=" +
                Uri.encode(query)
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    private fun addHeading(layout: LinearLayout, text: String) {
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
        text: String,
        action: () -> Unit
    ) {
        val button = Button(this)
        button.text = text
        button.textSize = 16f
        button.setTextColor(Color.WHITE)
        button.setBackgroundColor(purple)
        button.setOnClickListener { action() }

        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.bottomMargin = 8
        layout.addView(button, params)
    }
}
