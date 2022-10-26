package com.example.lawyeed

import Beans.OpenHelper
import Helpers.CircleTransform
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class SearchName : AppCompatActivity(){


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        var db: OpenHelper = OpenHelper(applicationContext)
        var personId = db.getUserId()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_name)
        supportActionBar?.hide();

        Picasso.get()
            .load(db.getUserImage())
            .resize(42, 42)
            .centerCrop()
            .transform(CircleTransform())
            .into(findViewById<ShapeableImageView>(R.id.person_image))
        println(db.getUserImage())

        findViewById<Button>(R.id.btnregresarsearch).setOnClickListener() {
            startActivity(Intent(this, Search::class.java))
        }



    }


}