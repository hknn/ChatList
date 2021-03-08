package com.chatlist.ui.chat

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chatlist.MainActivity
import com.chatlist.R
import com.chatlist.data.MyMessage
import com.chatlist.data.PreferenceRepository
import com.chatlist.ui.chat.adapter.ChatAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.chat_fragment) {

    @Inject
    lateinit var spRepository: PreferenceRepository
    private val viewModel by viewModels<ChatViewModel>()
    private lateinit var chatRv: RecyclerView
    private lateinit var imageSendTv: TextView
    private lateinit var messageEdit: EditText

    private val adapter by lazy {
        ChatAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spRepository.name?.let {
            (activity as MainActivity).setToolbarTitle(it)
        }

        initView()
        initAdapter()
        viewModel.getMessages()
        viewModel.messages.observe(viewLifecycleOwner, Observer {
            adapter.addList(it)
        })
    }

    private fun initView() {
        chatRv = requireView().findViewById(R.id.chatRv)
        messageEdit = requireView().findViewById(R.id.message_edit)
        imageSendTv = requireView().findViewById(R.id.image_send)
        imageSendTv.setOnClickListener {
            if (messageEdit.text.toString().isNullOrEmpty().not()) {
                adapter.addNew(MyMessage(messageEdit.text.toString(), spRepository.name ?: ""))
                messageEdit.setText("")
                chatRv.smoothScrollToPosition(adapter.itemCount)
            }
        }
    }

    private fun initAdapter() {
        chatRv.adapter = adapter
        val layoutManager = LinearLayoutManager(context)
        layoutManager.stackFromEnd = true
        chatRv.layoutManager = layoutManager
    }
}