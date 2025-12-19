package com.example.androidpemula

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DetailActivity : AppCompatActivity() {
    private lateinit var heroName: String
    private lateinit var heroDescription: String
    private lateinit var heroAddress: String
    private lateinit var heroPhone: String
    private lateinit var heroCoordinates: String

    companion object {
        const val key_hero = "key_hero"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val backButton = findViewById<ImageView>(R.id.back_button2)
        backButton.setOnClickListener {
            finish()
        }

        val dataHero = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(key_hero, Hero::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Hero>(key_hero)
        }

        val tvDetailName: TextView = findViewById(R.id.tv_detail_name)
        val tvDetailDescription: TextView = findViewById(R.id.tv_detail_description)
        val ivDetailPhoto: ImageView = findViewById(R.id.iv_detail_photo)

        val alamat = intent.getStringExtra("alamat")
        val notelp = intent.getStringExtra("notelp")
        val kordinat = intent.getStringExtra("kordinat")

        val tvDetailAlamat: TextView = findViewById(R.id.tv_detail_alamat)
        val tvDetailNotelp: TextView = findViewById(R.id.tv_detail_notelp)
        val tvDetailKordinat: TextView = findViewById(R.id.tv_detail_cordinates)

        if (dataHero != null) {
            tvDetailName.text = dataHero.name
            tvDetailDescription.text = intent.getStringExtra("description")
            Glide.with(this)
                .load(dataHero.photo)
                .into(ivDetailPhoto)
        }

        tvDetailAlamat.text = "Alamat: $alamat"
        tvDetailNotelp.text = "No. Telp: $notelp"
        tvDetailKordinat.text = "Kordinat: $kordinat"

        heroName = intent.getStringExtra("name") ?: ""
        heroDescription = intent.getStringExtra("description") ?: ""
        heroAddress = intent.getStringExtra("alamat") ?: ""
        heroPhone = intent.getStringExtra("notelp") ?: ""
        heroCoordinates = intent.getStringExtra("kordinat") ?: ""

        val shareButton: FloatingActionButton = findViewById(R.id.share_button)
        shareButton.setOnClickListener {
            shareText()
        }


    }
    private fun shareText() {
        val shareMessage = """
            Monumen: $heroName
            Deskripsi: $heroDescription
            Alamat: $heroAddress
            No. Telp: $heroPhone
            Kordinat: $heroCoordinates
        """.trimIndent()

        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareMessage)
            type = "text/plain"
        }
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}

