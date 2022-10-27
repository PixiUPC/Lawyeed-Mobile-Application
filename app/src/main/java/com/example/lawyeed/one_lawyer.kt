package com.example.lawyeed

import Beans.Notification
import Beans.OpenHelper
import Beans.service.API
import Beans.service.`class`.*
import Beans.service.`class`.Lawyer
import Helpers.CircleTransform
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lawyeed.notification.Adapter
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class one_lawyer: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var db: OpenHelper = OpenHelper(applicationContext)
        var personId = db.getUserId()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_lawyer)
        supportActionBar?.hide();

        val button_contratar = findViewById<Button>(R.id.buttonContratar)

        button_contratar.setOnClickListener{
            val showPopUp = popUpFragmentContratar()
            showPopUp.show((this as AppCompatActivity).supportFragmentManager, "showPopUp")
        }

        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://lawyeedapi.azurewebsites.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }

        getRetrofit().create(API::class.java)
            .getOneLawyer("personlawyers/"+ intent.extras?.getSerializable("id") )
            .enqueue(object :Callback<Lawyer?> {
                override fun onResponse(call: Call<Lawyer?>, response: Response<Lawyer?>) {

                    val item = response.body()!!
                    val lawyerNamePerson = findViewById<TextView>(R.id.lawyerName)
                    val lawyerDescriptionPerson = findViewById<TextView>(R.id.lawyerDescription)

                    lawyerNamePerson.text = item.fisrtName
                    lawyerDescriptionPerson.text = item.description

                }
                override fun onFailure(call: Call<Lawyer?>, t: Throwable) {
                    TODO("Not yet implemented")
                }


            })
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
                    val recycler = findViewById<RecyclerView>(R.id.recycler_cases_lawyer)
                    recycler.layoutManager= LinearLayoutManager(applicationContext)
                    recycler.adapter= com.example.lawyeed.cases.Adapter(listCases)
                }

                override fun onFailure(call: Call<List<CasesResponse>?>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
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

                    val recycler = findViewById<RecyclerView>(R.id.recycler_notifications_lawyer)
                    recycler.layoutManager= LinearLayoutManager(applicationContext)
                    recycler.adapter= Adapter(listNotification)
                }

                override fun onFailure(call: Call<List<NotificationResponse>?>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })


    }
}