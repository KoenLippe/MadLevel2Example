package com.example.madlevel2example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel2example.adapter.ReminderAdapter
import com.example.madlevel2example.databinding.ActivityReminderBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_reminder.*

class ReminderActivity : AppCompatActivity() {

    private val reminders = arrayListOf<Reminder>()
    private val reminderAdapter = ReminderAdapter(reminders)

    private lateinit var binding: ActivityReminderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReminderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
    }

    private fun initViews() {
        binding.btnAddReminder.setOnClickListener {
            val text = binding.etReminder.text.toString()
            addReminder(text)
        }

        binding.rvReminders.layoutManager = LinearLayoutManager(this@ReminderActivity, RecyclerView.VERTICAL, false)
        binding.rvReminders.adapter = reminderAdapter
        binding.rvReminders.addItemDecoration(DividerItemDecoration(this@ReminderActivity, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(binding.rvReminders)

    }

    private fun addReminder(reminderText: String) {
        if(reminderText.isNotBlank()){
            reminders.add(Reminder(reminderText))
            reminderAdapter.notifyDataSetChanged()
            binding.etReminder.text?.clear()
        } else {
            Snackbar.make(etReminder, "You must fill in the input field!", Snackbar.LENGTH_SHORT).show();
        }
    }

    private fun createItemTouchHelper(): ItemTouchHelper {
        val callback = object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
               return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                reminders.removeAt(position)
                reminderAdapter.notifyDataSetChanged()
            }
        }

        return ItemTouchHelper(callback)
    }
}