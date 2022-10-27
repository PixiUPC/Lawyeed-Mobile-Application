package com.example.lawyeed

import Beans.OpenHelper
import Helpers.CircleTransform
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class SearchName : AppCompatActivity(){

    lateinit var iniSearch: Button
    lateinit var searchname: EditText

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

        iniSearch = findViewById<Button>(R.id.btnIniSearchName)
        searchname = findViewById<EditText>(R.id.textInputEditTextNames)
        iniSearch.setOnClickListener {
            if (searchname.text.toString()!=""){
                val intent = Intent(this@SearchName, SearchResult::class.java)
                val type = "name"
                intent.putExtra("type", type)
                val name = searchname.text.toString()
                intent.putExtra("names", name)
                startActivity(intent)
            }
        }
        findViewById<Button>(R.id.btnregresarsearch).setOnClickListener() {
            startActivity(Intent(this, Search::class.java))
        }



    }


}