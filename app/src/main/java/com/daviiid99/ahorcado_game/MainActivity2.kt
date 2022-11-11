package com.daviiid99.ahorcado_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val intent = Intent(this, MainActivity::class.java)

        var button = findViewById<Button>(R.id.button2)

        button.setOnClickListener {
            startActivity(intent)
        }
    }
}