package com.flxrs.dankchat.player

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageButton
import android.widget.TextView
import com.google.android.exoplayer2.ui.PlayerView
import kotlinx.android.synthetic.main.device_player_view.view.*
import kotlinx.android.synthetic.main.main_fragment.view.*

open class DankPlayerView(context: Context, attrs: AttributeSet?) : PlayerView(context, attrs), DankPlayerViewUI {
    private val isFullScreen: Boolean = false
    private var titleView: TextView?
    private var closeButton: ImageButton?
    private var fullScreenButton: ImageButton?
    private var pauseButton: ImageButton?
    private var playButton: ImageButton?
    private var playerView: DankPlayerView?
    private var fullScreenListeners: HashSet<DankPlayerViewUI.OnFullScreenClickedListener> = HashSet()
    private var closeButtonListener: HashSet<DankPlayerViewUI.OnCloseButtonClickedListener> = HashSet()

    init {
        this.showController()
        this.playerView = player_view
        this.titleView = player_view.exo_title
        this.playButton = player_view.exo_play
        this.pauseButton = player_view.exo_pause
        this.fullScreenButton = player_view.exo_fullscreen
        fullScreenButton?.setOnClickListener {
            for(x in fullScreenListeners) x.onFullScreenClicked(!isFullScreen)
        }
        this.closeButton = player_view.player_close_button
        closeButton?.setOnClickListener {
            for(x in closeButtonListener) x.onCloseButtonClicked()
        }
    }

    override fun setTitle(title: String) {
        titleView?.text = title
    }

    override fun addCloseButtonClickedListener(listener: DankPlayerViewUI.OnCloseButtonClickedListener) {
        closeButtonListener.add(listener)
    }

    override fun addFullScreenButtonClickedListener(listener: DankPlayerViewUI.OnFullScreenClickedListener) {
        fullScreenListeners.add(listener)
    }

    override fun removeCloseButtonClickedListener(listener: DankPlayerViewUI.OnCloseButtonClickedListener) {
        closeButtonListener.remove(listener)
    }

    override fun removeFullScreenButtonClickedListener(listener: DankPlayerViewUI.OnFullScreenClickedListener) {
        fullScreenListeners.remove(listener)
    }


}