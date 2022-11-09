package com.example.lawyeed

import Beans.OpenHelper
import Beans.service.API
import Beans.service.`class`.OneCaseReponse
import Beans.service.`class`.PersonResponse
import Helpers.Circle
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
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

class one_case : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_case)
        supportActionBar?.hide();

        var db: OpenHelper = OpenHelper(applicationContext)

        Picasso.get()
            .load(db.getUserImage())
            .resize(42, 42)
            .centerCrop()
            .transform(Circle())
            .into(findViewById<ShapeableImageView>(R.id.person_image))
        println(db.getUserImage())


        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://lawyeedapi.azurewebsites.net/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        }

        getRetrofit().create(API::class.java)
            .getOneCase("consults/"+ intent.extras?.getSerializable("id") ).enqueue(object : Callback<OneCaseReponse?> {
                override fun onResponse(
                    call: Call<OneCaseReponse?>,
                    response: Response<OneCaseReponse?>
                ) {

                    var item = response.body()!!

                    var text_title = findViewById<TextView>(R.id.text_title)
                    var text_status = findViewById<TextView>(R.id.text_status)
                    var text_description = findViewById<TextView>(R.id.text_description)
                    var image_lawyer = findViewById<ImageView>(R.id.image_lawyer)
                    var lawyer_name = findViewById<TextView>(R.id.lawyer_name)
                    var lawyer_description = findViewById<TextView>(R.id.lawyer_description)

                    text_title.text = item.title
                    text_status.text = item.state
                    text_description.text = item.description
                    lawyer_name.text = item.lawyer.firstName + " " + item.lawyer.lastName
                    lawyer_description.text = item.lawyer.description

                    Picasso.get()
                        .load(item.lawyer.urlImage)
                        .resize(42, 42)
                        .centerCrop()
                        .transform(Circle())
                        .into(image_lawyer)

                    if(item.state  == "ABIERTO") {
                        text_status.setBackgroundResource(R.drawable.case_background_open)
                    } else {
                        text_status.setBackgroundResource(R.drawable.case_backfround_close)

                    }


                }

                override fun onFailure(call: Call<OneCaseReponse?>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }


}