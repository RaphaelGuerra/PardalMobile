package com.example.pardalmobile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_chat_lab4.*

class ChatActivity : AppCompatActivity() {

    companion object {
        const val CHAT_ACTIVITY = "CHAT_ACTIVITY"
        const val SHARED_PREFS = "sharedPrefs"
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_lab4)

        val messageList = mutableListOf<Message>()
        val aListAdapter = ListAdapter(messageList)
        chatList.adapter = aListAdapter
        val message = findViewById<TextView>(R.id.message)
        sendBtn.setOnClickListener { if (!message.text.isEmpty()){ val msg = Message(message.text.toString(), true)
            messageList.add(msg)
            message.text = ""
            aListAdapter.notifyDataSetChanged()}}
        receiveBtn.setOnClickListener { if (!message.text.isEmpty()){ val msg = Message(message.text.toString(), false)
            messageList.add(msg)
            message.text = ""
            aListAdapter.notifyDataSetChanged()}}
    }

    private class Message(val messageString: String, val isSend: Boolean)

    private class ListAdapter(msg: List<Message>): BaseAdapter() {
        private var messageList = msg

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val newView: View?
            if (messageList[position].isSend) {
                newView = LayoutInflater.from(parent?.context).inflate(R.layout.message_send_lab4, parent, false)
                val messageText = newView.findViewById<TextView>(R.id.message)
                messageText.text = messageList[position].messageString
            } else {
                newView = LayoutInflater.from(parent?.context).inflate(R.layout.message_receive_lab4, parent, false)
                val messageText = newView.findViewById<TextView>(R.id.message)
                messageText.text = messageList[position].messageString
            }
            return newView
        }

        override fun getItem(position: Int): Message {
            return messageList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return messageList.size
        }
    }
}