
package com.musicstar.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*
import android.media.MediaPlayer

class MainActivity : Activity() {

    private val bg = Color.rgb(15, 15, 25)
    private val purple = Color.rgb(130, 75, 255)

    private var player: MediaPlayer? = null

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

        fun heading(text: String, size: Float) {
            val t = TextView(this)
            t.text = text
            t.textSize = size
            t.gravity = Gravity.CENTER
            t.setTextColor(Color.WHITE)
            content.addView(t)
        }

        heading("🎵 MUSIC STAR", 30f)
        heading("Your Music, Your World", 15f)

        val search = EditText(this)
        search.hint = "गाने का Direct MP3 URL डालें"
        search.setTextColor(Color.WHITE)
        search.setHintTextColor(Color.LTGRAY)
        search.setSingleLine(true)
        search.setPadding(15, 15, 15, 15)
        search.setBackgroundColor(Color.rgb(40, 40, 55))
        content.addView(search)

        val status = TextView(this)
        status.text = "अपना कानूनी Audio URL डालें"
        status.textSize = 16f
        status.gravity = Gravity.CENTER
        status.setTextColor(Color.WHITE)
        status.setPadding(10, 25, 10, 25)
        content.addView(status)

        fun addButton(label: String, action: () -> Unit) {
            val b = Button(this)
            b.text = label
            b.textSize = 16f
            b.setTextColor(Color.WHITE)
            b.setBackgroundColor(purple)
            b.setOnClickListener { action() }

            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.bottomMargin = 8
            content.addView(b, params)
        }

        addButton("▶ PLAY SONG") {
            val url = search.text.toString().trim()

            if (!url.startsWith("https://") &&
                !url.startsWith("http://")) {
                status.text = "कृपया सही Audio URL डालें"
                return@addButton
            }

            try {
                player?.release()
                player = MediaPlayer()

                status.text = "गाना Loading हो रहा है..."

                player?.setOnPreparedListener {
                    it.start()
                    status.text = "🎵 Song Playing..."
                }

                player?.setOnErrorListener { _, _, _ ->
                    status.text = "गाना नहीं चल पाया। दूसरा URL डालें।"
                    true
                }

                player?.setDataSource(url)
                player?.prepareAsync()

            } catch (e: Exception) {
                status.text = "Playback Error"
            }
        }

        addButton("⏸ PAUSE") {
            if (player?.isPlaying == true) {
                player?.pause()
                status.text = "⏸ Song Paused"
            }
        }

        addButton("▶ RESUME") {
            player?.let {
                if (!it.isPlaying) {
                    try {
                        it.start()
                        status.text = "🎵 Song Playing"
                    } catch (e: Exception) {
                        status.text = "पहले गाना Play करें"
                    }
                }
            }
        }

        addButton("⏹ STOP") {
            player?.release()
            player = null
            status.text = "Song Stopped"
        }

        heading("🔥 MUSIC STAR", 22f)
        heading("Free Music Experience 💜", 14f)

        scroll.addView(content)
        root.addView(scroll)
        setContentView(root)
    }

    override fun onDestroy() {
        player?.release()
        player = null
        super.onDestroy()
    }
}
