package com.example.lawyeed

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btnEditarPerfil: Button = findViewById(R.id.btnEditarPerfil)

        val textNombre: TextView = findViewById(R.id.textNombre)
        val textDescripcion: TextView = findViewById(R.id.textDescripcion)
        val textEmail: TextView = findViewById(R.id.textEmail)
        val textEdad: TextView = findViewById(R.id.textEdad)

        /*val nombres: String? = intent.getStringExtra("nombresValue")
        val descripcion: String? = intent.getStringExtra("descripcionValue")
        val email: String? = intent.getStringExtra("emailValue")
        val edad: String? = intent.getStringExtra("edadValue")

        if(nombres != null) {
            textNombre.text = nombres.toString()
        }
        if(descripcion != null) {
            textDescripcion.text = descripcion.toString()
        }
        if(email != null) {
            textEmail.text = email.toString()
        }
        if(edad != null) {
            textEdad.text = edad.toString()
        }*/


        btnEditarPerfil.setOnClickListener() {
            val intent = Intent(this, ProfileEdit::class.java)
            /*intent.putExtra("nombresValue", textNombre.text.toString())
            intent.putExtra("descripcionValue", textDescripcion.text.toString())
            intent.putExtra("emailValue", textEmail.text.toString())
            intent.putExtra("edadValue", textEdad.text.toString())*/
            startActivity(intent)
        }
    }
}