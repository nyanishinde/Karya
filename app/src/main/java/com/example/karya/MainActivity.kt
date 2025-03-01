package com.example.karya

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import com.google.android.material.progressindicator.CircularProgressIndicator
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    private lateinit var dailyTaskProgressIndicator: CircularProgressIndicator
    private lateinit var weeklyGoalsProgressIndicator: CircularProgressIndicator
    private lateinit var monthlyGoalsProgressIndicator: CircularProgressIndicator
    private lateinit var dailyProgress: TextView
    private lateinit var weeklyProgress: TextView
    private lateinit var monthlyProgress: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Declaring all the views
        dailyTaskProgressIndicator = findViewById(R.id.cpiDailyTask)
        weeklyGoalsProgressIndicator = findViewById(R.id.cpiWeeklyGoals)
        monthlyGoalsProgressIndicator = findViewById(R.id.cpiMonthlyGoals)
        dailyProgress=findViewById(R.id.txtDailyProgress)
        weeklyProgress=findViewById(R.id.txtWeeklyProgress)
        monthlyProgress=findViewById(R.id.txtMonthlyProgress)

        //Initializing and setting progress value for the progress bars
        var progressValue = 75
        dailyTaskProgressIndicator.setProgress(progressValue,true)
        weeklyGoalsProgressIndicator.setProgress(progressValue,true)
        monthlyGoalsProgressIndicator.setProgress(progressValue,true)
        //setting progress text
        dailyProgress.text = "$progressValue%"
        weeklyProgress.text = "$progressValue%"
        monthlyProgress.text = "$progressValue%"

        //Initializing daily task layout and setting dialog on its click
        val dailyLayout = findViewById<CardView>(R.id.cardDailyTask)
        dailyLayout.setOnClickListener {
//            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
            val checklistDialog=CheckListDialogFragment()
            checklistDialog.show(supportFragmentManager,"ChecklistDialog")
        }



    }
}