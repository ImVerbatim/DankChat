package com.flxrs.dankchat.player.api

import androidx.annotation.Keep
import com.squareup.moshi.Json

sealed class HLSDtos {
    @Keep
    data class StreamUrls(
        @field:Json(name = "success") val isSuccess: Boolean,
        @field:Json(name = "urls") val urls: Map<String, String>
    )

}