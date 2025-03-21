package com.example.karya

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MonthlyGoals : AppCompatActivity() {

    private lateinit var imgBtnAdd : ImageButton
    private lateinit var rvMonthlyGoals:RecyclerView
    private lateinit var toolbar: Toolbar

    private lateinit var goalTrackingAdapter: WeeklyGoalsAdapter
    private lateinit var goalsViewModel: GoalsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_monthly_goals)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Setting toolbar to have activity title and back button
        toolbar = findViewById(R.id.toolbarMonthlyGoals)
        setSupportActionBar(toolbar)
        supportActionBar?.title=""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //getting the back icon to change its color
        val navIcon = toolbar.navigationIcon
        navIcon?.setTint(ContextCompat.getColor(this,R.color.black))
        //Getting menu icon and changing its color
        toolbar.overflowIcon?.setTint(ContextCompat.getColor(this,R.color.black))

        //Initializing recyclerview and setting adapter on it
        rvMonthlyGoals = findViewById(R.id.rvGoals)
        rvMonthlyGoals.layoutManager= GridLayoutManager(this,3)
        goalTrackingAdapter= WeeklyGoalsAdapter()
        rvMonthlyGoals.adapter=goalTrackingAdapter

        goalsViewModel= ViewModelProvider(this).get(GoalsViewModel::class.java)
        goalsViewModel.goalsWithProgress.observe(this) { goals->
            goalTrackingAdapter.submitList(goals)
        }

        //Initializing image button to add new element
        imgBtnAdd = findViewById(R.id.btnAddGoals)
        imgBtnAdd.setOnClickListener {
//            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
            val addNewGoalDialog = AddGoalDialogFragment()
            addNewGoalDialog.show(supportFragmentManager,"AddNewGoalDialog")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed() // Go back when clicked
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}