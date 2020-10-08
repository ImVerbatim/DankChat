package com.flxrs.dankchat.player

import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.Player.EventListener
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.MediaSourceFactory
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.DebugTextViewHelper
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.android.synthetic.main.device_player_view.view.*


class DankPlayer(
    playerView: DankPlayerView,
    private val context: Context,
    private val appCompatActivity: AppCompatActivity?
) :
    EventListener,
    DankPlayerViewUI.OnFullScreenClickedListener,
    DankPlayerViewUI.OnCloseButtonClickedListener,
    DankPlayerViewUI.OnPlayClickedListener, DankPlayerViewUI.OnSettingsButtonClickedListener, PlaybackPreparer {
    private var isShowingTrackSelectionDialog: Boolean = false
    private lateinit var debugViewHelper: DebugTextViewHelper
    private lateinit var trackSelectorParameters: DefaultTrackSelector.Parameters
    private lateinit var trackSelector: DefaultTrackSelector
    private lateinit var lastSeenTrackGroupArray: TrackGroupArray
    private lateinit var channelName: String
    private lateinit var streamUrl: String
    private lateinit var mediaSource: HlsMediaSource
    private lateinit var mediaItem: MediaItem
    private lateinit var dataSourceFactory: DefaultDataSourceFactory
    private lateinit var player: SimpleExoPlayer
    private val dankPlayerView: DankPlayerView = playerView
    private var isDebugMode: Boolean = false

    init {
        initPlayer()
    }

    fun initPlayer() {
        dataSourceFactory = DefaultDataSourceFactory(context)
        val mediaSourceFactory: MediaSourceFactory = DefaultMediaSourceFactory(dataSourceFactory)

        trackSelector = DefaultTrackSelector(context)
        updateTrackSelectorParameters()
        trackSelector.parameters = trackSelectorParameters
        val renderersFactory = DefaultRenderersFactory(context)
        player = SimpleExoPlayer.Builder(context, renderersFactory)
            .setMediaSourceFactory(mediaSourceFactory)
            .setTrackSelector(trackSelector)
            .build()
        player.addListener(this)
        player.playWhenReady = true
        dankPlayerView.setPlaybackPreparer(this)
        player.prepare()
        dankPlayerView.player = player

        dankPlayerView.dank_image.setOnClickListener {
            showDebugMode(!isDebugMode)
            isDebugMode = !isDebugMode
        }

    }

    private fun showDebugMode(isDebug: Boolean) {
        if(isDebug) {
            dankPlayerView.showDebugView(true)
            debugViewHelper = DebugTextViewHelper(player, dankPlayerView.getDebugView())
            debugViewHelper.start()
        }
        else
            dankPlayerView.showDebugView(false)
    }

    private fun updateTrackSelectorParameters() {
            trackSelectorParameters = trackSelector.parameters
    }


    fun play(url: String, channel: String) {
        streamUrl = url
        channelName = channel
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
        dankPlayerView.addPlayButtonClickedListener(this)
        dankPlayerView.addSettingsButtonClickedListener(this)
    }

    private fun removeAsListeners() {
        dankPlayerView.removeCloseButtonClickedListener(this)
        dankPlayerView.removeFullScreenButtonClickedListener(this)
        dankPlayerView.removePlayButtonClickedListener(this)
        dankPlayerView.removeSettingsButtonClickedListener(this)

    }

    override fun onFullScreenClicked(isFullScreen: Boolean) {

    }

    override fun onCloseButtonClicked() {
        player.stop()
        dankPlayerView.visibility = View.GONE
        //removeAsListeners()
    }

    override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
        when(playbackState) {
            Player.STATE_IDLE -> {

            }
            Player.STATE_BUFFERING -> {
                dankPlayerView.setShouldShowBuffer(true)
            }
            Player.STATE_READY -> {
                if (player.isPlaying)
                    dankPlayerView.setShouldShowBuffer(false)
            }
            Player.STATE_ENDED -> {

            }
            else -> {
                // Unknown state
            }
        }
    }

    override fun onPlayClickedListener() {
        player.stop()
        play(this.streamUrl, this.channelName)
    }

    override fun onSettingsButtonClicked() {
        if(!isShowingTrackSelectionDialog && TrackSelectionDialog.willHaveContent(trackSelector)) {
            isShowingTrackSelectionDialog = true
            val trackSelectionDialog = TrackSelectionDialog.createForTrackSelector(
                trackSelector  /* onDismissListener= */
            ) { _ -> isShowingTrackSelectionDialog = false }

            trackSelectionDialog.show(appCompatActivity?.supportFragmentManager!!, null)
        }
    }

    fun addCloseButtonClickedListener(closeButtonClickedListener: DankPlayerViewUI.OnCloseButtonClickedListener) {
        dankPlayerView.addCloseButtonClickedListener(closeButtonClickedListener)
    }

    fun addFullScreenClickedListener(fullScreenClickedListener: DankPlayerViewUI.OnFullScreenClickedListener) {
        dankPlayerView.addFullScreenButtonClickedListener(fullScreenClickedListener)
    }

    override fun preparePlayback() {
        player.prepare()
    }

    fun isViewActive(): Boolean {
        return dankPlayerView.visibility == View.VISIBLE
    }

    fun stop() {
        player.stop()
    }

    fun destroy() {
        player.release()
        removeAsListeners()
        dankPlayerView.visibility = View.GONE
    }
}