package com.chatlist.ui.chat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.chatlist.R
import com.chatlist.data.Message
import com.chatlist.data.MyMessage
import kotlinx.android.synthetic.main.item_mymessage.view.*
import kotlinx.android.synthetic.main.item_visitor.view.*
import kotlinx.android.synthetic.main.item_visitor.view.visitor_iv
import java.text.SimpleDateFormat

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val list = ArrayList<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            MessageType.VISITOR.ordinal -> {
                VisitorViewHolder(inflater.inflate(R.layout.item_visitor, parent, false))
            }
            else -> {
                MyMessageViewHolder(inflater.inflate(R.layout.item_mymessage, parent, false))
            }
        }
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MyMessageViewHolder -> {
                holder.bindModel(list[position] as MyMessage)
            }
            is VisitorViewHolder -> {
                holder.bindModel(list[position] as Message)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (list[position]) {
            is Message -> MessageType.VISITOR.ordinal
            is MyMessage -> MessageType.MYMESSAGE.ordinal
            else -> -1
        }
    }

    inner class VisitorViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindModel(model: Message) {
            Glide.with(itemView.context).load(model.user.avatarURL).into(itemView.visitor_iv)
            itemView.visitor_username_tv.text = model.user.nickname
            itemView.visitor_tv.text = model.text
            itemView.visitor_time.text = getDate(model.timestamp.toLong())
        }
    }

    fun getDate(milisecond: Long): String {
        val dateFormat = SimpleDateFormat("HH:mm dd/MM/yyyy")
        return dateFormat.format(milisecond)
    }

    inner class MyMessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindModel(model: MyMessage) {
            itemView.mymessage_tv.text = model.text
            Glide.with(itemView.context).load("https://randomuser.me/api/portraits/men/78.jpg")
                .into(itemView.mymessage_iv)
            itemView.mymessage_nickname_tv.text = model.nickname
            itemView.mymessage_time.text = getDate(System.currentTimeMillis())
        }
    }

    fun addList(items: List<Any>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun addNew(item: MyMessage) {
        list.add(item)
        notifyDataSetChanged()
    }

    enum class MessageType {
        VISITOR,
        MYMESSAGE
    }
}