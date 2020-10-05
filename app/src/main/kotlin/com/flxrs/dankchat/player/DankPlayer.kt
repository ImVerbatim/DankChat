package com.flxrs.dankchat.player

import android.content.Context
import android.view.View
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.Player.EventListener
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import java.util.*

class DankPlayer(playerView: DankPlayerView, private val context: Context) : EventListener, DankPlayerViewUI.OnFullScreenClickedListener, DankPlayerViewUI.OnCloseButtonClickedListener {
    private lateinit var mediaSource: HlsMediaSource
    private lateinit var mediaItem: MediaItem
    private lateinit var dataSourceFactory: DefaultDataSourceFactory
    private lateinit var player: SimpleExoPlayer
    private val dankPlayerView: DankPlayerView = playerView

    interface EventListener {
        fun onPlayClicked()
    }

    init {
        initPlayer()
    }

    fun initPlayer() {
        player = SimpleExoPlayer.Builder(context).build()
        dankPlayerView.player = player
        dataSourceFactory = DefaultDataSourceFactory(context)
        player.addListener(this)

    }

    fun play(url: String, channel: String) {
        dankPlayerView.visibility = View.VISIBLE
        dankPlayerView.setTitle(channel)
        addListeners()
        mediaItem = MediaItem.fromUri(url)
        mediaSource = HlsMediaSource.Factory(dataSourceFactory).createMediaSource(mediaItem)
        player.setMediaSource(mediaSource)
        player.playWhenReady = true
        player.prepare()
    }

    private fun addListeners() {
        dankPlayerView.addCloseButtonClickedListener(this)
        dankPlayerView.addFullScreenButtonClickedListener(this)
    }

    private fun removeAsListeners() {
        dankPlayerView.removeCloseButtonClickedListener(this)
        dankPlayerView.removeFullScreenButtonClickedListener(this)
    }

    override fun onFullScreenClicked(isFullScreen: Boolean) {
        TODO("Not yet implemented")
    }

    override fun onCloseButtonClicked() {
        player.stop()
        removeAsListeners()
        dankPlayerView.visibility = View.GONE
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        when(playbackState) {
            Player.STATE_IDLE -> {

            }
            Player.STATE_BUFFERING -> {
                dankPlayerView.setShouldShowBuffer(true)
            }
            Player.STATE_READY -> {
                if(player.isPlaying)
                    dankPlayerView.setShouldShowBuffer(false)
            }
            Player.STATE_ENDED -> {

            }
            else -> {
                // Unknown state
            }
        }
    }
}