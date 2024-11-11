package com.example.uts_mobile_0930

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val name = intent.getStringExtra("name")
        val age = intent.getStringExtra("age")
        val gender = intent.getStringExtra("gender")

        val resultTextView: TextView = findViewById(R.id.textViewResult)
        resultTextView.text = "Nama: $name\nUsia: $age\nJenis Kelamin: $gender"
    }
}
