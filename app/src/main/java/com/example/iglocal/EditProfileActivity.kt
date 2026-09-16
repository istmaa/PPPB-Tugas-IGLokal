package com.example.iglocal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.iglocal.MainActivity.Companion.EXTRA_BIO
import com.example.iglocal.MainActivity.Companion.EXTRA_NAME
import com.example.iglocal.MainActivity.Companion.EXTRA_USERNAME

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val etUsername = findViewById<EditText>(R.id.etUsername)
        val etName = findViewById<EditText>(R.id.etName)
        val etBio = findViewById<EditText>(R.id.etBio)
        val btnSave = findViewById<Button>(R.id.btnSave)

        // Retrieve existing data passed from MainActivity
        val currentUsername = intent.getStringExtra(EXTRA_USERNAME) ?: ""
        val currentName = intent.getStringExtra(EXTRA_NAME) ?: ""
        val currentBio = intent.getStringExtra(EXTRA_BIO) ?: ""

        etUsername.setText(currentUsername)
        etName.setText(currentName)
        etBio.setText(currentBio)

        btnSave.setOnClickListener {
            val updatedUsername = etUsername.text.toString()
            val updatedName = etName.text.toString()
            val updatedBio = etBio.text.toString()

            val resultIntent = Intent().apply {
                putExtra(EXTRA_USERNAME, updatedUsername)
                putExtra(EXTRA_NAME, updatedName)
                putExtra(EXTRA_BIO, updatedBio)
            }

            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
