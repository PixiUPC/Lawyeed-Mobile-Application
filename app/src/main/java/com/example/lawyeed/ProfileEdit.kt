package com.example.lawyeed

import Beans.OpenHelper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class ProfileEdit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit)
        supportActionBar!!.hide()

        val btnActualizarPerfil: Button = findViewById(R.id.btnActualizarPerfil)

        var db: OpenHelper = OpenHelper(applicationContext)

        Picasso.get()
            .load(db.getUserImage())
            .into(findViewById<ShapeableImageView>(R.id.myphotomini))
        Picasso.get()
            .load(db.getUserImage())
            .into(findViewById<ShapeableImageView>(R.id.myphoto))
        println(db.getUserImage())

        val editNombre: EditText = findViewById(R.id.editNombre)
        val editDescripcion: EditText = findViewById(R.id.editDescripcion)
        val editEmail: EditText = findViewById(R.id.editEmail)
        val editEdad: EditText = findViewById(R.id.editEdad)

        /*val nombres: String? = intent.getStringExtra("nombresValue")
        val descripcion: String? = intent.getStringExtra("descripcionValue")
        val email: String? = intent.getStringExtra("emailValue")
        val edad: String? = intent.getStringExtra("edadValue")

        editNombre.setText(nombres.toString())
        editDescripcion.setText(descripcion.toString())
        editEmail.setText(email.toString())
        editEdad.setText(edad.toString())*/

        btnActualizarPerfil.setOnClickListener() {
            val intent = Intent(this, ProfileUpdated::class.java)
            /*intent.putExtra("nombresValue", editNombre.text.toString())
            intent.putExtra("descripcionValue", editDescripcion.text.toString())
            intent.putExtra("emailValue", editEmail.text.toString())
            intent.putExtra("edadValue", editEdad.text.toString())*/
            startActivity(intent)
        }
    }
}