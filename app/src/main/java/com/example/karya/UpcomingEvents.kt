package com.example.karya

import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class UpcomingEvents : AppCompatActivity() {

    private lateinit var addEvent : ImageButton
    private lateinit var rvEvents: RecyclerView
    private lateinit var eventAdapter: AdapterUpcomingEvents
    private val eventList = mutableListOf(
        DCEventDetails("10","Board Meeting","10:00 AM"),
        DCEventDetails("12","Team Meeting","10:00 AM"),
        DCEventDetails("13","Group Meeting","10:00 AM"),
        DCEventDetails("15","Friends Meeting","10:00 AM"),
        DCEventDetails("18","get to gather","10:00 AM"),
        DCEventDetails("20","Wedding anniversary","10:00 AM"),
        DCEventDetails("22","Party","10:00 AM"),
        DCEventDetails("24","Diwali","10:00 AM"),
        DCEventDetails("26","Holi","10:00 AM"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_upcoming_events)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //initializing and setting toolbar
        val toolbar : Toolbar = findViewById(R.id.toolbarEvents)
        setSupportActionBar(toolbar)
        supportActionBar?.title=""
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val navIcon = toolbar.navigationIcon
        navIcon?.setTint(ContextCompat.getColor(this,R.color.black))

        //Declaring recycler view and setting adapter to it
        rvEvents = findViewById(R.id.recyclerViewEvents)
        rvEvents.layoutManager = LinearLayoutManager(this)
        eventAdapter = AdapterUpcomingEvents(eventList)
        rvEvents.adapter = eventAdapter

        //Initializing image button to add events
        addEvent = findViewById(R.id.btnAddEvents)
        addEvent.setOnClickListener {
            val reminderDialog = ReminderDialogFragment()
            reminderDialog.show(supportFragmentManager,"ReminderDialog")
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}