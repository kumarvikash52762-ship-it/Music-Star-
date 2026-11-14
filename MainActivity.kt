
package com.musicstar.app

import android.app.Activity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient

class MainActivity : Activity() {
    private lateinit var webView: WebView

    @Suppress("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView = WebView(this)
        webView.settings.javaScriptEnabled = true
        webView.settings.domStorageEnabled = true
        webView.settings.mediaPlaybackRequiresUserGesture = true
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()

        setContentView(webView)

        webView.loadDataWithBaseURL(
            "https://musicstar.app/",
            """
            <!DOCTYPE html>
            <html>
            <head>
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <style>
            body {
                background:#10101b;
                color:white;
                font-family:Arial;
                text-align:center;
                padding:15px;
            }
            h1 {color:#ffcc33;}
            .btn {
                background:#28283e;
                color:white;
                border:1px solid #555;
                padding:15px;
                margin:6px;
                border-radius:12px;
                font-size:16px;
                width:90%;
            }
            input {
                width:85%;
                padding:14px;
                border-radius:10px;
                border:0;
                margin:12px 0;
                font-size:16px;
            }
            .play {
                background:#e91e63;
                color:white;
                border:0;
                padding:14px;
                border-radius:10px;
                width:90%;
                font-size:16px;
            }
            </style>
            </head>
            <body>

            <h1>🎵 MUSIC STAR</h1>
            <p>Music • Videos • Movies</p>

            <input id="search"
            placeholder="Search Song, Video or Movie">

            <button class="play" onclick="searchMusic()">
            🔍 Search
            </button>

            <h3>Choose Your Entertainment</h3>

            <button class="btn" onclick="music()">
            🎵 Music
            </button>

            <button class="btn" onclick="videos()">
            🎬 Video Songs
            </button>

            <button class="btn" onclick="movies()">
            🍿 Movies
            </button>

            <h3>Music Categories</h3>

            <button class="btn" onclick="category('Hindi songs')">
            🇮🇳 Hindi
            </button>

            <button class="btn" onclick="category('Bhojpuri songs')">
            Bhojpuri
            </button>

            <button class="btn" onclick="category('Punjabi songs')">
            Punjabi
            </button>

            <button class="btn" onclick="category('Bollywood songs')">
            Bollywood
            </button>

            <script>
            function go(url) {
                window.location.href = url;
            }

            function music() {
                go('https://music.youtube.com/');
            }

            function videos() {
                go('https://www.youtube.com/results?search_query=Hindi+video+songs');
            }

            function movies() {
                go('https://archive.org/details/feature_films');
            }

            function category(name) {
                go('https://www.youtube.com/results?search_query='
                + encodeURIComponent(name));
            }

            function searchMusic() {
                var q = document.getElementById('search').value;
                if(q.trim() == '') return;
                go('https://www.youtube.com/results?search_query='
                + encodeURIComponent(q));
            }
            </script>

            </body>
            </html>
            """.trimIndent(),
            "text/html",
            "UTF-8",
            null
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        webView.saveState(outState)
        super.onSaveInstanceState(outState)
    }

    @Deprecated("Deprecated in Android API")
    override fun onBackPressed() {
        if (::webView.isInitialized && webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
