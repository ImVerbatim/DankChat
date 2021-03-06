package com.flxrs.dankchat.player.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

class M3U8Fetch @Inject constructor() {

    private val service = Retrofit.Builder()
        .baseUrl(M3U8_FETCH_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(HLSStreamFetchService::class.java)

    suspend fun getStreamM3U8(channel: String): HLSDtos.StreamUrls? = withContext(Dispatchers.IO) {
        val response = service.getM3U8(M3U8_FETCH_URL + "streamapi.py?url=twitch.tv%2F$channel")
        response.bodyOrNull
    }

    companion object {
        const val M3U8_FETCH_URL = "https://www.pwn.sh/tools/"
    }
}

private val <T> retrofit2.Response<T>.bodyOrNull
    get() = when {
        isSuccessful -> body()
        else -> null
    }
