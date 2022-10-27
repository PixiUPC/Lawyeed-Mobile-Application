package com.example.lawyeed

import Beans.OpenHelper
import Beans.service.API
import Beans.service.`class`.PersonResponse
import Helpers.CircleTransform
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
/*
        val userId = db.getUserId()

        getRetrofit().create(API::class.java)
            .getLawyers("person/${userId}").enqueue(object : Callback<List<PersonResponse>?> {
                override fun onResponse(
                    call: Call<List<PersonResponse>?>,
                    response: Response<List<PersonResponse>?>
                ) {
                    var counter = 0;
                    for(item in response.body()!!) {
                        if(counter < 5) {
                            listLawyers.add(
                                Beans.Lawyer(
                                    item.id,
                                    item.firstName,
                                    item.lastName,
                                    item.email,
                                    "",
                                    item.description,
                                    item.urlImage,
                                    item.type,
                                    item.specialty,
                                    item.wonCases,
                                    item.totalCases,
                                    item.lostCases
                                )
                            )
                            counter++;
                        }
                    }
                    var recycler = findViewById<RecyclerView>(R.id.recycler_lawyers)
                    val horizontalLayoutManagaer = LinearLayoutManager(applicationContext, LinearLayoutManager.HORIZONTAL, false)
                    recycler.layoutManager = horizontalLayoutManagaer
                    recycler.adapter= com.example.lawyeed.lawyers_home.Adapter(listLawyers)
                }

                override fun onFailure(call: Call<List<PersonResponse>?>, t: Throwable) {
                    t?.printStackTrace()
                }

            })

*/

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

        btnComprarSubscripcion.setOnClickListener() {
            //val intent = Intent(this, Subscription::class.java)
            //startActivity(intent)
        }
    }
}