package com.example.androidpemula

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Profile : AppCompatActivity() {
    private lateinit var namaDiri: String
    private lateinit var idDiri: String
    private lateinit var emailBangkit: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        val namaDiriTextView: TextView = findViewById(R.id.nama_diri)
        val idDiriTextView: TextView = findViewById(R.id.id_diri)
        val emailBangkitTextView: TextView = findViewById(R.id.email_bangkit)

        namaDiri = namaDiriTextView.text.toString()
        idDiri = idDiriTextView.text.toString()
        emailBangkit = emailBangkitTextView.text.toString()

        val shareButton: FloatingActionButton = findViewById(R.id.share_button2)
        shareButton.setOnClickListener {
            shareProfile()
        }
    }
    private fun shareProfile() {
        val shareMessage = """
            Nama: $namaDiri
            ID: $idDiri
            Email: $emailBangkit
        """.trimIndent()

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareMessage)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}