package com.chatlist.ui.chat

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chatlist.data.ChatApi
import com.chatlist.data.Message
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class ChatViewModel @ViewModelInject constructor(private val api: ChatApi) : ViewModel() {

    private val executor: Executor = Executors.newSingleThreadExecutor()
    val messages = MutableLiveData<List<Message>>()

    fun getMessages() {
        executor.execute {
            val response = api.getChatMessages().execute()
            if (response.isSuccessful) {
                messages.postValue(response.body()?.messages)
            }
        }
    }
}