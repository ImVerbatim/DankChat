package com.flxrs.dankchat.player

interface DankPlayerViewUI {

    fun setTitle(title: String)

    fun addCloseButtonClickedListener(listener: DankPlayerViewUI.OnCloseButtonClickedListener)
    fun addFullScreenButtonClickedListener(listener: DankPlayerViewUI.OnFullScreenClickedListener)
    fun removeCloseButtonClickedListener(listener: DankPlayerViewUI.OnCloseButtonClickedListener)
    fun removeFullScreenButtonClickedListener(listener: DankPlayerViewUI.OnFullScreenClickedListener)
    interface OnFullScreenClickedListener {
        fun onFullScreenClicked(isFullScreen: Boolean)
    }

    interface OnCloseButtonClickedListener {
        fun onCloseButtonClicked()
    }


}