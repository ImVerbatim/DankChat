package com.flxrs.dankchat.player

import android.content.Context
import android.util.AttributeSet
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.ui.PlayerView

open class DankPlayerView : PlayerView {
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        this.showController()

    }
}