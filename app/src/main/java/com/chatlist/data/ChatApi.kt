package com.chatlist.data

import retrofit2.Call
import retrofit2.http.GET

interface ChatApi {
    @GET("api/jsonBlob/62455171-0fb1-11eb-9f83-ffcd873e5c3a")
    fun getChatMessages(): Call<ChatResponse>
}