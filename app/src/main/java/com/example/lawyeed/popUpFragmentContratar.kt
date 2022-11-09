package com.example.lawyeed

import Beans.service.API
import Beans.service.`class`.CasoItem
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class popUpFragmentContratar : DialogFragment() {
    lateinit var service: API

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout para este fragmento
        return inflater.inflate(R.layout.popup_contratar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://lawyeedapi.azurewebsites.net/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        service = retrofit.create(API::class.java)


        val button_crear_caso = view.findViewById<Button>(R.id.buttonCrearCaso)

        button_crear_caso.setOnClickListener{
            val titulo_caso = view.findViewById<EditText>(R.id.editTextTituloCaso).text.toString()
            val descriptionCaso = view.findViewById<EditText>(R.id.editTextDescriptionCaso).text.toString()

            val caso = CasoItem(titulo_caso,null,descriptionCaso,null,null)

            service.createdCase(caso).enqueue(object : Callback<CasoItem>{
                override fun onResponse(call: Call<CasoItem>, response: Response<CasoItem>) {
                    val addedCase= response.body()
                    Toast.makeText(context, response.body()?.description, Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<CasoItem>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }


    }

}