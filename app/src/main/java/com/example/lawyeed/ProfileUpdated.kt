package com.example.lawyeed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ProfileUpdated : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_updated)

        val btnCerrarEditarMiPerfil: Button = findViewById(R.id.btnCerrarEditarPerfil)

        /*val nombres: String? = intent.getStringExtra("nombresValue")
        val descripcion: String? = intent.getStringExtra("descripcionValue")
        val email: String? = intent.getStringExtra("emailValue")
        val edad: String? = intent.getStringExtra("edadValue")*/

        btnCerrarEditarMiPerfil.setOnClickListener() {
            val intent = Intent(this, Profile::class.java)
            /*intent.putExtra("nombresValue", nombres.toString())
            intent.putExtra("descripcionValue", descripcion.toString())
            intent.putExtra("emailValue", email.toString())
            intent.putExtra("edadValue", edad.toString())*/
            startActivity(intent)
        }
    }
}