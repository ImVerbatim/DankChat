package com.flxrs.dankchat.player

interface DankPlayerViewUI {

    fun setTitle(title: String)

    fun addFullScreenButtonClickedListener(listener: DankPlayerViewUI.OnFullScreenClickedListener)
    fun removeFullScreenButtonClickedListener(listener: DankPlayerViewUI.OnFullScreenClickedListener)
    fun addCloseButtonClickedListener(listener: DankPlayerViewUI.OnCloseButtonClickedListener)
    fun removeCloseButtonClickedListener(listener: DankPlayerViewUI.OnCloseButtonClickedListener)
    fun addPlayButtonClickedListener(listener: DankPlayerViewUI.OnPlayClickedListener)
    fun removePlayButtonClickedListener(listener: DankPlayerViewUI.OnPlayClickedListener)
    fun addSettingsButtonClickedListener(listener: DankPlayerViewUI.OnSettingsButtonClickedListener)
    fun removeSettingsButtonClickedListener(listener: DankPlayerViewUI.OnSettingsButtonClickedListener)
    interface OnPlayClickedListener {
        fun onPlayClickedListener()
    }
    interface OnFullScreenClickedListener {
        fun onFullScreenClicked(isFullScreen: Boolean)
    }

    interface OnCloseButtonClickedListener {
        fun onCloseButtonClicked()
    }

    interface OnSettingsButtonClickedListener {
        fun onSettingsButtonClicked()
    }
}