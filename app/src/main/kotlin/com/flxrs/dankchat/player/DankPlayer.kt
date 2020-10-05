package com.flxrs.dankchat.player

import android.content.Context
import android.view.View
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

class DankPlayer {
    private lateinit var mediaSource: HlsMediaSource
    private lateinit var mediaItem: MediaItem
    private lateinit var dataSourceFactory: DefaultDataSourceFactory
    private lateinit var player: SimpleExoPlayer
    private val dankPlayerView: PlayerView
    private val context : Context

    constructor(playerView: PlayerView, context: Context) {
        this.dankPlayerView = playerView
        this.context = context

        initPlayer()
    }

    fun initPlayer() {
        player = SimpleExoPlayer.Builder(context as Context).build()
        dankPlayerView.player = player
        dataSourceFactory = DefaultDataSourceFactory(context)

    }

    fun play(url: String) {
        dankPlayerView.visibility = View.VISIBLE
        mediaItem = MediaItem.fromUri(url)
        mediaSource = HlsMediaSource.Factory(dataSourceFactory).createMediaSource(mediaItem)
        player.setMediaSource(mediaSource)
        player.setPlayWhenReady(true);
        player.prepare()
    }
}