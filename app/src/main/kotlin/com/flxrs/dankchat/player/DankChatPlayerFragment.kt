package com.flxrs.dankchat.player

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.flxrs.dankchat.R
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

const val HLS_STATIC_URL = "https://bitdash-a.akamaihd.net/content/MI201109210084_1/m3u8s/f08e80da-bf1d-4e3d-8899-f0f6155f6efa.m3u8"
const val STATE_RESUME_WINDOW = "resumeWindow"
const val STATE_RESUME_POSITION = "resumePosition"
const val STATE_PLAYER_FULLSCREEN = "playerFullscreen"
const val STATE_PLAYER_PLAYING = "playerOnPlay"

class DankChatPlayerFragment : Fragment() {
    private lateinit var playerView: PlayerView
    private lateinit var exoPlayer: SimpleExoPlayer
    private lateinit var dataSourceFactory: DataSource.Factory

    private var currentWindow = 0
    private var playbackPosition: Long = 0
    private var isFullscreen = false
    private var isPlayerPlaying = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: DankChatPlayerFragment = DataBindingUtil.inflate(inflater,
            R.layout.player_view, container,false)
        playerView = binding.view as PlayerView

        return binding.view
    }


    private fun initPlayer(){
//        exoPlayer = SimpleExoPlayer.Builder(context).build()
//        val videoSource = HlsMediaSource.Factory(dataSourceFactory)
//            .createMediaSource(MediaItem.fromUri(Uri.parse(HLS_STATIC_URL)))

        with(exoPlayer) {
            playWhenReady = isPlayerPlaying
            seekTo(currentWindow, playbackPosition)
            prepare()
        }
        playerView.player = exoPlayer

    }

}