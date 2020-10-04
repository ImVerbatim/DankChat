package com.flxrs.dankchat.player.api

import com.flxrs.dankchat.player.api.M3U8Fetch.Companion.M3U8_FETCH_URL
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface HLSStreamFetchService {
    @GET
    suspend fun getM3U8(@Url url: String): Response<HLSDtos.StreamUrls>
}
