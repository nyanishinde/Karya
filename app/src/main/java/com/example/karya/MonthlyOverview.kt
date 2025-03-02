package com.example.karya

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MonthlyOverview : AppCompatActivity() {

    private val taskNames= mutableListOf("Running","Meditation","Journaling","Cooking","Study","Gym")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_monthly_overview)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Creating variable for recyclerview and setting layout manager
        val recyclerViewTaskNames : RecyclerView = findViewById(R.id.rvTaskNames)
        recyclerViewTaskNames.layoutManager = LinearLayoutManager(this)
        //Creating adapter and setting it on recycler view
        val rvAdapter = AdapterTaskNames(taskNames)
        recyclerViewTaskNames.adapter=rvAdapter
    }
}