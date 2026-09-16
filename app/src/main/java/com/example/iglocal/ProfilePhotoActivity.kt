package com.example.iglocal

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.iglocal.MainActivity.Companion.EXTRA_NAME

class ProfilePhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_photo)

        val imgProfileLarge = findViewById<ImageView>(R.id.imgProfileLarge)
        val tvProfileName = findViewById<TextView>(R.id.tvProfileName)
        val btnKembali = findViewById<Button>(R.id.btnKembali)

        imgProfileLarge.clipToOutline = true

        // Retrieve current profile name passed via Explicit Intent
        val currentName = intent.getStringExtra(EXTRA_NAME) ?: ""
        tvProfileName.text = currentName

        // Close activity and return to MainActivity
        btnKembali.setOnClickListener {
            finish()
        }
    }
}
