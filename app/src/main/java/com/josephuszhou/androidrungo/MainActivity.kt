package com.josephuszhou.androidrungo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_run_go.setOnClickListener(this)
        btn_visit_index.setOnClickListener(this)
        btn_visit_hello.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v) {
            btn_run_go -> {
                // Run Go HTTP server
                Runtime.getRuntime().exec(applicationInfo.nativeLibraryDir + "/server.so")
            }
            btn_visit_index -> {
                // Open web browser
                val intent = Intent().apply {
                    action = "android.intent.action.VIEW"
                    data = Uri.parse("http://127.0.0.1:8888/")
                }
                startActivity(intent)
            }
            btn_visit_hello -> {
                // Open web browser
                val intent = Intent().apply {
                    action = "android.intent.action.VIEW"
                    data = Uri.parse("http://127.0.0.1:8888/hello")
                }
                startActivity(intent)
            }
        }
    }
}
