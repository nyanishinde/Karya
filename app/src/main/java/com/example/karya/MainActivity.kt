package com.example.karya

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import com.db.williamchart.view.BarChartView
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

        //This method is used to add all the functionality to the graph shown on the main screen
        setWeeklyOverviewChart()

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

        //Initializing Upcoming Events layout and setting dialog on its click
        val upcomingEventsLayout = findViewById<CardView>(R.id.cardUpcomingEvents)
        upcomingEventsLayout.setOnClickListener {
            startActivity(Intent(this,UpcomingEvents::class.java))
        }

        //Setting intent to go from this activity to weekly activity
        val weeklyGoalsLayout = findViewById<CardView>(R.id.cardWeeklyGoals)
        weeklyGoalsLayout.setOnClickListener {
            startActivity(Intent(this,WeeklyGoals::class.java))
        }

        //Setting intent to go from this activity to monthly activity
        val monthlyGoalsLayout = findViewById<CardView>(R.id.cardMonthlyGoals)
        monthlyGoalsLayout.setOnClickListener {
            startActivity(Intent(this,MonthlyGoals::class.java))
        }

    }

    fun setWeeklyOverviewChart(){
        //Initializing all view in the card
        val weeklyOverview : CardView = findViewById(R.id.cardWeeklyOverView)
        val range : TextView = findViewById(R.id.txtRange)
        val barChart : BarChartView = findViewById(R.id.barChart)
        val message : TextView = findViewById(R.id.tvMessage)
        val followupMessage : TextView = findViewById(R.id.tvFollowupMessage)
        val image : ImageView = findViewById(R.id.imgEmogi)

        //Adding titles and values for the graph
        val barSet = listOf(
            "Mon" to 5.0f,
            "Tue" to 5.3f,
            "Wed" to 4.2f,
            "Thur" to 6.5f,
            "Fri" to 3.7f,
            "Sat" to 1.0f,
            "Sun" to 0.0f
        )
        //Setting animation time for the graph
        val animationDuration = 1000L

        barChart.animation.duration = animationDuration
        barChart.animate(barSet)
        barChart.spacing = 80.0f  //used to set spacing between the bars
        barChart.labelsSize=32.0f
        barChart.labelsFont = ResourcesCompat.getFont(this,R.font.lato_bold)

        //Setting click event on the card
        weeklyOverview.setOnClickListener {
            startActivity(Intent(this,MonthlyOverview::class.java))
        }
    }

}