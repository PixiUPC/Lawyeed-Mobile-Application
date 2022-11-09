package com.example.lawyeed

import Beans.OpenHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        supportActionBar!!.hide()

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://lawyeedapi.azurewebsites.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        }
        val btnEditarPerfil: Button = findViewById(R.id.btnEditarPerfil)
        val btnComprarSubscripcion: Button = findViewById(R.id.btnComprarSubscripcion)

        var db: OpenHelper = OpenHelper(applicationContext)

        Picasso.get()
            .load(db.getUserImage())
            .into(findViewById<ShapeableImageView>(R.id.myphotomini))
        Picasso.get()
            .load(db.getUserImage())
            .into(findViewById<ShapeableImageView>(R.id.myphoto))
        println(db.getUserImage())

        val name = db.getUserName()
        val description = db.getUserDescription()
        val email = db.getUserEmail()

        val textNombre: TextView = findViewById(R.id.textNombre)
        val textDescripcion: TextView = findViewById(R.id.textDescripcion)
        val textEmail: TextView = findViewById(R.id.textEmail)
        val textEdad: TextView = findViewById(R.id.textEdad)

        textNombre.text = name
        textDescripcion.text = description
        textEmail.text = email

        btnEditarPerfil.setOnClickListener() {
            val intent = Intent(this, ProfileEdit::class.java)
            startActivity(intent)
        }

        btnComprarSubscripcion.setOnClickListener() {
            val intent = Intent(this, Subscription::class.java)
            startActivity(intent)
        }
    }
}