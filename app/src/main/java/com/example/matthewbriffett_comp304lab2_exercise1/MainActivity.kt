package com.example.matthewbriffett_comp304lab2_exercise1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val enterButton = findViewById<Button>(R.id.buttonEnter)
        enterButton.setOnClickListener {
            val intent = Intent(this, HomeSelectMenu::class.java)
            startActivity(intent)
        }
    }
}