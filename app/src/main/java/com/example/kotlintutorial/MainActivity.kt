package com.example.kotlintutorial

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URI

class MainActivity : AppCompatActivity() {

    private val uri = URI("wss://qfdc32yvk1.execute-api.ap-northeast-1.amazonaws.com/Prod")
    private val client = MyWebSocketClient(this, uri)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()

        // 接続ボタンが押下された時
        btnConnect.setOnClickListener {
            client.connect()
            it.isEnabled = false
            btnDisconnect.isEnabled = true
        }

        // 切断ボタンが押下された時
        btnDisconnect.setOnClickListener {
            client.close()
            btnConnect.isEnabled = true
            btnDisconnect.isEnabled = false
        }

        // 送信ボタンが押下された時
        btnSendMessage.setOnClickListener {
            client.send("{ \"message\": \"sendmessage\", \"data\": \"${editSendMessage.text.toString()}\" }")
//            messageList.append(editSendMessage.text.toString())
        }

    }
}