package com.vharya.aktifitas2c

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private var selectedCalendar: Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val datePicker = findViewById<EditText>(R.id.date_picker)
        val timePicker = findViewById<EditText>(R.id.time_picker)

        datePicker.setOnClickListener {
            DatePickerDialog(
                this,
                { view, year, month, date ->
                    selectedCalendar.set(year, month, date)

                    val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                    datePicker.setText(formatter.format(selectedCalendar.time))
                },
                selectedCalendar.get(Calendar.YEAR),
                selectedCalendar.get(Calendar.MONTH),
                selectedCalendar.get(Calendar.DAY_OF_MONTH),
            ).show()
        }

        timePicker.setOnClickListener {
            TimePickerDialog(
                this,
                { view, hour, minute ->
                    selectedCalendar.set(
                        selectedCalendar.get(Calendar.YEAR),
                        selectedCalendar.get(Calendar.MONTH),
                        selectedCalendar.get(Calendar.DAY_OF_MONTH),
                        hour,
                        minute
                    )

                    val formatter = SimpleDateFormat("HH:mm", Locale.getDefault())
                    timePicker.setText(formatter.format(selectedCalendar.time))
                },
                selectedCalendar.get(Calendar.HOUR),
                selectedCalendar.get(Calendar.MINUTE),
                true
            ).show()
        }
    }
}