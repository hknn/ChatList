package com.chatlist.data

data class ChatResponse(val messages: List<Message>)

data class Message(val id: String, val text: String, val timestamp: Int, val user: User)

data class User(val id: Int, val avatarURL: String, val nickname: String)

data class MyMessage(val text: String, val nickname: String)