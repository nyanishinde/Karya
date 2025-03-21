package com.example.karya

import android.icu.util.Calendar
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.progressindicator.CircularProgressIndicator
import org.w3c.dom.Text

class MonthlyOverview : AppCompatActivity() {

    private lateinit var circularProgressIndicator: CircularProgressIndicator
    private lateinit var progressPercentage : TextView
    private lateinit var progressDays : TextView
    private lateinit var rvMonthlyProgress : RecyclerView
    private lateinit var tasksViewModel : TasksViewModel
    private lateinit var taskNamesAdapter : TaskNamesAdapter

    private val dailyTaskProgressItem = mutableListOf(
        DCDailyTaskProgressOverview(R.drawable.img_running,"Running",23,31),
        DCDailyTaskProgressOverview(R.drawable.img_meditation,"Meditation",26,31),
        DCDailyTaskProgressOverview(R.drawable.img_journaling,"Journaling",4,31),
        DCDailyTaskProgressOverview(R.drawable.img_cooking,"Cooking",15,31),
        DCDailyTaskProgressOverview(R.drawable.img_study,"Study",17,31),
        DCDailyTaskProgressOverview(R.drawable.img_cardio,"Gym",7,31)
    ) 

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_monthly_overview)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Creating toolbar and applying it
        val toolbar = findViewById<Toolbar>(R.id.toolbarOverview)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //Enabling the back button in toolbar
        supportActionBar?.title=""//setting title as empty string
        //getting the back icon to change its color
        val navIcon = toolbar.navigationIcon
        navIcon?.setTint(ContextCompat.getColor(this,R.color.black))

        //Initialing items in the overview card
        circularProgressIndicator = findViewById(R.id.cpiOverview)
        progressPercentage = findViewById(R.id.txtTaskPercentage)
        progressDays = findViewById(R.id.txtTaskDays)

        val calendar = Calendar.getInstance()
        val totalDays = getTotalDays(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1) //getting total days in current months
        val performedDays = 10 //Set this dynamically
        circularProgressIndicator.setProgress(75,true)
        progressPercentage.text = "75%"
        progressDays.text = "$performedDays/$totalDays"

        //Creating variable for recyclerview and setting layout manager
        val taskNames : RecyclerView = findViewById(R.id.rvTaskNames)
        taskNames.layoutManager = LinearLayoutManager(this)
        //Creating adapter and setting it on recycler view
        val adapterTaskNames = TasksAdapter()
        taskNames.adapter=adapterTaskNames

        //Creating recycler view of tasks and setting adapter on it
        rvMonthlyProgress = findViewById(R.id.rvOverviewTaskProgress)
        rvMonthlyProgress.layoutManager=LinearLayoutManager(this)
        taskNamesAdapter= TaskNamesAdapter()
        rvMonthlyProgress.adapter=taskNamesAdapter

        //Initializing view model
        tasksViewModel = ViewModelProvider(this).get(TasksViewModel::class.java)

        //Observing all views
        tasksViewModel.allTasks.observe(this) {tasks->
            adapterTaskNames.submitList(tasks)
        }

    }

    private fun getTotalDays(year:Int,month:Int):Int{
        val calender = Calendar.getInstance()
        calender.set(year,month,1)
        return calender.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed() // Go back when clicked
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}