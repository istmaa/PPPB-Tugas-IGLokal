package com.example.iglocal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NAME = "EXTRA_NAME"
        const val EXTRA_BIO = "EXTRA_BIO"
    }

    private var currentName: String = ""
    private var currentBio: String = ""

    private val editProfileLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val updatedName = data?.getStringExtra(EXTRA_NAME)
            val updatedBio = data?.getStringExtra(EXTRA_BIO)

            if (!updatedName.isNullOrEmpty()) {
                currentName = updatedName
                findViewById<TextView>(R.id.tvProfileName).text = updatedName
            }
            if (updatedBio != null) {
                currentBio = updatedBio
                findViewById<TextView>(R.id.tvBio).text = updatedBio
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imgProfile = findViewById<ImageView>(R.id.imgProfile)
        val tvProfileName = findViewById<TextView>(R.id.tvProfileName)
        val tvBio = findViewById<TextView>(R.id.tvBio)
        val btnEditProfile = findViewById<Button>(R.id.btnEditProfile)

        imgProfile.clipToOutline = true

        // Initialize state or restore from savedInstanceState
        if (savedInstanceState != null) {
            currentName = savedInstanceState.getString(EXTRA_NAME, tvProfileName.text.toString())
            currentBio = savedInstanceState.getString(EXTRA_BIO, tvBio.text.toString())
            tvProfileName.text = currentName
            tvBio.text = currentBio
        } else {
            currentName = tvProfileName.text.toString()
            currentBio = tvBio.text.toString()
        }

        // Profile Photo navigation via Explicit Intent (startActivity)
        imgProfile.setOnClickListener {
            val intent = Intent(this@MainActivity, ProfilePhotoActivity::class.java).apply {
                putExtra(EXTRA_NAME, currentName)
            }
            startActivity(intent)
        }

        // Edit Profile navigation via Activity Result Launcher
        btnEditProfile.setOnClickListener {
            val intent = Intent(this@MainActivity, EditProfileActivity::class.java).apply {
                putExtra(EXTRA_NAME, currentName)
                putExtra(EXTRA_BIO, currentBio)
            }
            editProfileLauncher.launch(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(EXTRA_NAME, currentName)
        outState.putString(EXTRA_BIO, currentBio)
    }
}