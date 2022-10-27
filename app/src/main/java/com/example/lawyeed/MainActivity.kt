package com.example.lawyeed

import Beans.Notification
import Beans.OpenHelper
import Beans.service.API
import Beans.service.`class`.CasesResponse
import Beans.service.`class`.NotificationResponse
import Beans.service.`class`.PersonResponse
import Helpers.CircleTransform
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyeed.notification.Adapter
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://lawyeedapi.azurewebsites.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        }
        var db: OpenHelper = OpenHelper(applicationContext)
        var personId = db.getUserId()


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Picasso.get()
            .load(db.getUserImage())
            .resize(42, 42)
            .centerCrop()
            .transform(CircleTransform())
            .into(findViewById<ShapeableImageView>(R.id.person_image))
        println(db.getUserImage())


        supportActionBar?.hide();

        findViewById<Button>(R.id.btnNotification).setOnClickListener() {
            startActivity(Intent(this, com.example.lawyeed.Notification::class.java))
        }
        findViewById<Button>(R.id.btnCases).setOnClickListener() {
            startActivity(Intent(this, Cases::class.java))
        }
        findViewById<Button>(R.id.btnSearch).setOnClickListener() {
            startActivity(Intent(this, Search::class.java))
        }



        //Lista de Abogados
        val listLawyers = mutableListOf<Beans.Lawyer>()

        getRetrofit().create(API::class.java)
            .getLawyers("personlawyers/").enqueue(object : Callback<List<PersonResponse>?> {
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



        //Lista de Casos
        val listCases = mutableListOf<Beans.Cases>()

        getRetrofit().create(API::class.java)
            .getCases("persons/$personId/consults").enqueue(object : Callback<List<CasesResponse>?> {
                override fun onResponse(
                    call: Call<List<CasesResponse>?>,
                    response: Response<List<CasesResponse>?>
                ) {
                    var counter = 0;
                    for(item in response.body()!!) {
                        if(counter < 3) {
                            listCases.add(
                                Beans.Cases(
                                    item.id,
                                    item.title,
                                    item.description,
                                    item.status
                                )
                            )
                            counter++;
                        }
                    }
                    val recycler = findViewById<RecyclerView>(R.id.recycler_cases)
                    recycler.layoutManager= LinearLayoutManager(applicationContext)
                    recycler.adapter= com.example.lawyeed.cases.Adapter(listCases)
                }

                override fun onFailure(call: Call<List<CasesResponse>?>, t: Throwable) {
                    t?.printStackTrace()
                }

            })



        //Lista de Notificaciones
        val listNotification = mutableListOf<Notification>()

        getRetrofit().create(API::class.java)
            .getNotifications("notifications/persons/$personId").enqueue(object :
                Callback<List<NotificationResponse>?> {
                override fun onResponse(
                    call: Call<List<NotificationResponse>?>,
                    response: Response<List<NotificationResponse>?>
                ) {
                    var counter = 0;
                    for(item in response.body()!!) {
                        if(counter < 3) {
                            listNotification.add(Notification(item.id,item.title,item.description,item.consult.id))
                        }
                        counter++;
                    }

                    val recycler = findViewById<RecyclerView>(R.id.recycler_notifications)
                    recycler.layoutManager= LinearLayoutManager(applicationContext)
                    recycler.adapter= Adapter(listNotification)
                }

                override fun onFailure(call: Call<List<NotificationResponse>?>, t: Throwable) {
                    t?.printStackTrace()
                }

            })
    }
}