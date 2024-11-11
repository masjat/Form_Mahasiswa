package com.example.uts_mobile_0930

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class FormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val nameEditText: EditText = findViewById(R.id.editTextName)
        val ageEditText: EditText = findViewById(R.id.editTextAge)
        val genderGroup: RadioGroup = findViewById(R.id.radioGroupGender)
        val submitButton: Button = findViewById(R.id.buttonSubmit)

        val sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
        nameEditText.setText(sharedPreferences.getString("name", ""))
        ageEditText.setText(sharedPreferences.getString("age", ""))
        val gender = sharedPreferences.getString("gender", "")
        if (gender == "Laki-laki") {
            findViewById<RadioButton>(R.id.radioMale).isChecked = true
        } else {
            findViewById<RadioButton>(R.id.radioFemale).isChecked = true
        }

        submitButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val ageText = ageEditText.text.toString()
            val selectedGenderId = genderGroup.checkedRadioButtonId
            val selectedGender = findViewById<RadioButton>(selectedGenderId).text.toString()

            if (name.isEmpty() || ageText.isEmpty() || selectedGenderId == -1) {
                Toast.makeText(this, "Semua bidang harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val age = ageText.toIntOrNull()
            if (age == null || age !in 1..100) {
                Toast.makeText(this, "Usia harus berupa angka antara 1 dan 100!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            val editor = sharedPreferences.edit()
            editor.putString("name", name)
            editor.putString("age", ageText)
            editor.putString("gender", selectedGender)
            editor.apply()


            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("name", name)
            intent.putExtra("age", ageText)
            intent.putExtra("gender", selectedGender)
            startActivity(intent)
        }
    }
}
