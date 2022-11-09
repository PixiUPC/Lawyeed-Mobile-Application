package com.example.lawyeed

import Beans.Notification
import Beans.OpenHelper
import Beans.service.API
import Beans.service.`class`.NotificationResponse
import Beans.service.`class`.PersonResponse
import Helpers.CircleTransform
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
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

class Search : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {

        var db: OpenHelper = OpenHelper(applicationContext)
        var personId = db.getUserId()



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        supportActionBar?.hide();

        Picasso.get()
            .load(db.getUserImage())
            .resize(42, 42)
            .centerCrop()
            .transform(CircleTransform())
            .into(findViewById<ShapeableImageView>(R.id.person_image))
        println(db.getUserImage())

        findViewById<AppCompatButton>(R.id.btnregresarmain).setOnClickListener() {
            startActivity(Intent(this, MainActivity::class.java))
        }
        findViewById<AppCompatButton>(R.id.btnSearchName).setOnClickListener() {
            startActivity(Intent(this, SearchName::class.java))
        }
        findViewById<AppCompatButton>(R.id.btnSearchSpecialty).setOnClickListener() {
            val intent = Intent(this@Search, SearchResult::class.java)
            val type = "all"
            intent.putExtra("type", type)
            startActivity(intent)
        }
        /*findViewById<AppCompatButton>(R.id.btnSearchSpecialty).setOnClickListener() {
            startActivity(Intent(this, SearchSpecialty::class.java))
        }*/



    }


}