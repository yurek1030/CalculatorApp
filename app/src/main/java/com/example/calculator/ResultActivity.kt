package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    lateinit var activityResult : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        activityResult = findViewById(R.id.result)
        val message = intent.getStringExtra("final_result")
        activityResult.text = message
    }
    fun onClickBack(view: View){
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("activityResult", activityResult.text)
        startActivity(intent)
    }
}