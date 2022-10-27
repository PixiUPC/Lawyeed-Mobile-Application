package com.example.lawyeed

import Beans.OpenHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class ProfileUpdated : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_updated)
        supportActionBar!!.hide()

        val btnCerrarEditarMiPerfil: Button = findViewById(R.id.btnCerrarEditarPerfil)

        var db: OpenHelper = OpenHelper(applicationContext)

        Picasso.get()
            .load(db.getUserImage())
            .into(findViewById<ShapeableImageView>(R.id.myphotomini))
        Picasso.get()
            .load(db.getUserImage())
            .into(findViewById<ShapeableImageView>(R.id.myphoto))
        println(db.getUserImage())

        btnCerrarEditarMiPerfil.setOnClickListener() {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
        }
    }
}