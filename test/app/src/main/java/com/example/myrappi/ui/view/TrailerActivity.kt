package com.example.myrappi.ui.view


import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.myrappi.R

import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.google.android.youtube.player.YouTubeStandalonePlayer


class TrailerActivity : AppCompatActivity() {//, YouTubePlayer.OnInitializedListener
    private val TAG = "YoutubeActivity"
    var videoId : String =""
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trailer)

        videoId = intent.getStringExtra("keyTrailer").toString()

        /*val url = "https://www.youtube.com/watch?v=xCjIJMydI3s&t=975s" // your URL here
        val mediaPlayer: MediaPlayer? = MediaPlayer().apply {
            setAudioStreamType(AudioManager.STREAM_MUSIC)
            setDataSource(url)
            prepare() // might take long! (for buffering, etc)
            start()
        }*/


//        val ytPlayer = findViewById<View>(R.id.ytPlayer) as YouTubePlayerView
//
//        ytPlayer.initialize(
//            "AIzaSyBfj3oF1gNXc8vt8fJe2yRIVUGHOQ4Yh68",
//            object : YouTubePlayer.OnInitializedListener {
//                // Implement two methods by clicking on red
//                // error bulb inside onInitializationSuccess
//                // method add the video link or the playlist
//                // link that you want to play In here we
//                // also handle the play and pause
//                // functionality
//                override fun onInitializationSuccess(
//                    provider: YouTubePlayer.Provider,
//                    youTubePlayer: YouTubePlayer, b: Boolean
//                ) {
//                    youTubePlayer.loadVideo(videoId)
//                    youTubePlayer.play()
//                }
//
//                // Inside onInitializationFailure
//                // implement the failure functionality
//                // Here we will show toast
//                override fun onInitializationFailure(
//                    provider: YouTubePlayer.Provider,
//                    youTubeInitializationResult: YouTubeInitializationResult
//                ) {
//                    Toast.makeText(applicationContext, "Video player Failed", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            })


//        val playerView = YouTubePlayerView(this)
//        playerView.layoutParams = ConstraintLayout.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
//        layout.addView(playerView)
//
//        playerView.initialize(getString(R.string.GOOGLE_API_KEY), this)

       openYoutubeStandAlonePlayer(videoId,true)
       // val intent = YouTubeStandalonePlayer.createVideoIntent(this, "AIzaSyBfj3oF1gNXc8vt8fJe2yRIVUGHOQ4Yh68", videoId)
        //startActivity(intent)
    }

    fun openYoutubeStandAlonePlayer(VideoID: String, autoplay: Boolean = false, lightMode: Boolean = false) {
        val intent = YouTubeStandalonePlayer.createVideoIntent(
            this@TrailerActivity,
            "AIzaSyBfj3oF1gNXc8vt8fJe2yRIVUGHOQ4Yh68", //Developer Api Key
            VideoID,
            0, //startIndex
            autoplay,
            true
        )
        startActivity(intent)
    }
//    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, youTubePlayer: YouTubePlayer?,
//                                         wasRestored: Boolean) {
//        Log.d(TAG, "onInitializationSuccess: provider is ${provider?.javaClass}")
//        Log.d(TAG, "onInitializationSuccess: youTubePlayer is ${youTubePlayer?.javaClass}")
//        Toast.makeText(this, "Initialized Youtube Player successfully", Toast.LENGTH_SHORT).show()
//
//        youTubePlayer?.setPlayerStateChangeListener(playerStateChangeListener)
//        youTubePlayer?.setPlaybackEventListener(playbackEventListener)
//
//        if (!wasRestored) {
//            youTubePlayer?.cueVideo(videoId)
//        }
//    }
//
//    override fun onInitializationFailure(provider: YouTubePlayer.Provider?,
//                                         youTubeInitializationResult: YouTubeInitializationResult?) {
//        val REQUEST_CODE = 0
//
//        if (youTubeInitializationResult?.isUserRecoverableError == true) {
//            youTubeInitializationResult.getErrorDialog(this, REQUEST_CODE).show()
//        } else {
//            val errorMessage = "There was an error initializing the YoutubePlayer ($youTubeInitializationResult)"
//            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
//        }
//    }
//
//    private val playbackEventListener = object: YouTubePlayer.PlaybackEventListener {
//        override fun onSeekTo(p0: Int) {
//        }
//
//        override fun onBuffering(p0: Boolean) {
//        }
//
//        override fun onPlaying() {
//            Toast.makeText(this@TrailerActivity, "Good, video is playing ok", Toast.LENGTH_SHORT).show()
//        }
//
//        override fun onStopped() {
//            Toast.makeText(this@TrailerActivity, "Video has stopped", Toast.LENGTH_SHORT).show()
//        }
//
//        override fun onPaused() {
//            Toast.makeText(this@TrailerActivity, "Video has paused", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private val playerStateChangeListener = object: YouTubePlayer.PlayerStateChangeListener {
//        override fun onAdStarted() {
//            Toast.makeText(this@TrailerActivity, "Click Ad now, make the video creator rich!", Toast.LENGTH_SHORT).show()
//        }
//
//        override fun onLoading() {
//        }
//
//        override fun onVideoStarted() {
//            Toast.makeText(this@TrailerActivity, "Video has started", Toast.LENGTH_SHORT).show()
//        }
//
//        override fun onLoaded(p0: String?) {
//        }
//
//        override fun onVideoEnded() {
//            Toast.makeText(this@TrailerActivity, "Congratulations! You've completed another video.", Toast.LENGTH_SHORT).show()
//        }
//
//        override fun onError(p0: YouTubePlayer.ErrorReason?) {
//        }
//    }
}