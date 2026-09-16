package com.example.iglocal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.iglocal.MainActivity.Companion.EXTRA_BIO
import com.example.iglocal.MainActivity.Companion.EXTRA_NAME

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val etName = findViewById<EditText>(R.id.etName)
        val etBio = findViewById<EditText>(R.id.etBio)
        val btnSave = findViewById<Button>(R.id.btnSave)

        // Retrieve existing data passed from MainActivity
        val currentName = intent.getStringExtra(EXTRA_NAME) ?: ""
        val currentBio = intent.getStringExtra(EXTRA_BIO) ?: ""

        etName.setText(currentName)
        etBio.setText(currentBio)

        btnSave.setOnClickListener {
            val updatedName = etName.text.toString()
            val updatedBio = etBio.text.toString()

            val resultIntent = Intent().apply {
                putExtra(EXTRA_NAME, updatedName)
                putExtra(EXTRA_BIO, updatedBio)
            }

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
