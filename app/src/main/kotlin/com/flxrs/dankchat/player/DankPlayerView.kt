package com.flxrs.dankchat.player

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.exoplayer2.ui.DebugTextViewHelper
import com.google.android.exoplayer2.ui.PlayerView
import kotlinx.android.synthetic.main.device_player_view.view.*
import kotlinx.android.synthetic.main.main_fragment.view.*

open class DankPlayerView(context: Context, attrs: AttributeSet?) : PlayerView(context, attrs), DankPlayerViewUI {
    private var bufferingIcon: ProgressBar
    private var isFullScreen: Boolean = false
    private var titleView: TextView
    private var closeButton: ImageButton
    private var fullScreenButton: ImageButton
    private var pauseButton: ImageButton
    private var playButton: ImageButton
    private var playerView: DankPlayerView
    private var playerSettingsButton: ImageButton
    private var debugTextView: TextView
    private var dankChatImageView: ImageView
    private var fullScreenListeners: HashSet<DankPlayerViewUI.OnFullScreenClickedListener> = HashSet()
    private var closeButtonListener: HashSet<DankPlayerViewUI.OnCloseButtonClickedListener> = HashSet()
    private var playListeners: HashSet<DankPlayerViewUI.OnPlayClickedListener> = HashSet()
    private var settingsButtonListeners: HashSet<DankPlayerViewUI.OnSettingsButtonClickedListener> = HashSet()
    init {
        this.showController()
        this.playerView = player_view
        this.titleView = player_view.exo_title
        this.playButton = player_view.exo_play
        playButton.setOnClickListener {
            for(x in playListeners)x.onPlayClickedListener()
        }
        this.pauseButton = player_view.exo_pause
        this.fullScreenButton = player_view.exo_fullscreen
        this.bufferingIcon = player_view.progress_bar
        fullScreenButton.setOnClickListener {
            for(x in fullScreenListeners) x.onFullScreenClicked(!isFullScreen)
            isFullScreen = !isFullScreen
        }
        this.closeButton = player_view.player_close_button
        closeButton.setOnClickListener {
            for(x in closeButtonListener) x.onCloseButtonClicked()
        }

        this.playerSettingsButton = player_view.player_settings
        playerSettingsButton.setOnClickListener {
            for(x in settingsButtonListeners) x.onSettingsButtonClicked()
        }

        this.debugTextView = player_view.debug_text_view
        this.dankChatImageView = player_view.dankChatImageView
    }

    override fun setTitle(title: String) {
        titleView.text = title
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

    override fun addPlayButtonClickedListener(listener: DankPlayerViewUI.OnPlayClickedListener) {
        playListeners.add(listener)
    }

    override fun removePlayButtonClickedListener(listener: DankPlayerViewUI.OnPlayClickedListener) {
        playListeners.remove(listener)
    }

    override fun addSettingsButtonClickedListener(listener: DankPlayerViewUI.OnSettingsButtonClickedListener) {
        settingsButtonListeners.add(listener)
    }

    override fun removeSettingsButtonClickedListener(listener: DankPlayerViewUI.OnSettingsButtonClickedListener) {
        settingsButtonListeners.add(listener)
    }

    override fun removeFullScreenButtonClickedListener(listener: DankPlayerViewUI.OnFullScreenClickedListener) {
        fullScreenListeners.remove(listener)
    }

    fun setShouldShowBuffer(b: Boolean) {
        bufferingIcon.isVisible = b
    }

    fun getDebugView(): TextView {
        return debugTextView
    }

    fun showDebugView(b: Boolean) {
        if(b)
            debugTextView.visibility = View.VISIBLE
        else
            debugTextView.visibility = View.GONE
    }


}