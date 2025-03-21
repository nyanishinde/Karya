package com.example.karya

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class WeeklyGoals : AppCompatActivity() {

    private lateinit var imgBtnAdd : ImageButton
    private lateinit var goalsRecyclerView: RecyclerView

    private lateinit var goalTrackingAdapter: WeeklyGoalsAdapter
    private lateinit var goalsViewModel: GoalsViewModel

    private val goalsList = mutableListOf(
        DCGoals("Running",R.drawable.img_running,30,R.color.primary),
        DCGoals("Gym",R.drawable.img_cardio,45,R.color.warning),
        DCGoals("Journaling",R.drawable.img_journaling,60,R.color.primary),
        DCGoals("Study",R.drawable.img_study,90,R.color.error),
        DCGoals("Meditation",R.drawable.img_meditation,10,R.color.primary),
        DCGoals("Cooking",R.drawable.img_cooking,40,R.color.warning),
        DCGoals("Coding",R.drawable.img_coding,40,R.color.warning)
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_weekly_goals)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Setting toolbar to have a activity title with back button and option menu
        val toolbar = findViewById<Toolbar>(R.id.toolbarWeeklyGoals)
        setSupportActionBar(toolbar)
        //Changing the default title(app name) in toolbar
        supportActionBar?.title=""
        //setting the back button in toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //getting the back icon to change its color
        val navIcon = toolbar.navigationIcon
        navIcon?.setTint(ContextCompat.getColor(this,R.color.black))
        //Getting menu icon and changing its color
        toolbar.overflowIcon?.setTint(ContextCompat.getColor(this,R.color.black))

        //Initializing recyclerview and setting adapter on it
        goalsRecyclerView = findViewById(R.id.rvGoals)
        goalsRecyclerView.layoutManager= GridLayoutManager(this,3)
        goalTrackingAdapter = WeeklyGoalsAdapter()
        goalsRecyclerView.adapter=goalTrackingAdapter

        goalsViewModel= ViewModelProvider(this).get(GoalsViewModel::class.java)
        goalsViewModel.goalsWithProgress.observe(this) { goals->
            goalTrackingAdapter.submitList(goals)
        }

        //Initializing image button to add new element
        imgBtnAdd = findViewById(R.id.btnAddGoals)
        imgBtnAdd.setOnClickListener {
            val addGoalDialog = AddGoalDialogFragment()
            addGoalDialog.show(supportFragmentManager,"AddGoalDialog")
//            Toast.makeText(this, "Clicked", Toast.LENGTH_SHORT).show()
        }
    }


    //Creating options menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.weekly_menu,menu)
        return true
    }

    //Setting the actions of menu options
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home->{
                onBackPressedDispatcher.onBackPressed()
                true
            }
            R.id.deleteAll -> {
//                Toast.makeText(this, "All deleted", Toast.LENGTH_SHORT).show()
                showDeleteAllGoalsDialog()
                true
            }
            R.id.allDone -> {
                Toast.makeText(this, "All Completed", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //Creating the dialog box to show when user tries to delete all goals at once
    private fun showDeleteAllGoalsDialog(){

        //Creating a alert dialog builder to create our dialog
        val deleteDialogBuilder = AlertDialog.Builder(this)
        deleteDialogBuilder.setTitle("Delete all")
        deleteDialogBuilder.setMessage("Are you sure to delete all?")

        //Adding positive button
        deleteDialogBuilder.setPositiveButton("yes"){dialog,_->
            //Implement the logic to delete all goal records here
            Toast.makeText(this, "All deleted", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        //Adding negative button
        deleteDialogBuilder.setNeutralButton("No"){dialog,_->
            dialog.dismiss()
        }
        //Creating the dialog here
        val deleteAllGoalsDialog = deleteDialogBuilder.create()

        //Changing the color of buttons on dialog box
        deleteAllGoalsDialog.setOnShowListener {
            deleteAllGoalsDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(resources.getColor(R.color.text_light))
            deleteAllGoalsDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(resources.getColor(R.color.text_hint))
        }
        //Showing dialog here
        deleteAllGoalsDialog.show()
    }
}