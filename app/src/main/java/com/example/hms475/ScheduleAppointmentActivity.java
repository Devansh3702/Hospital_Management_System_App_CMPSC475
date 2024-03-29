package com.example.hms475;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ScheduleAppointmentActivity extends AppCompatActivity {

    private static final String DATE_SPINNER_SELECTED_POSITION = "date_spinner_selected_position";
    private static final String TIME_SPINNER_SELECTED_POSITION = "time_spinner_selected_position";

    private Spinner dateSpinner;
    private Spinner timeSpinner;
    private Button scheduleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduleappointment);

        dateSpinner = findViewById(R.id.date_spinner);
        timeSpinner = findViewById(R.id.time_spinner);
        scheduleButton = findViewById(R.id.schedule_button);

        // Populate the date spinner with calendar dates
        ArrayAdapter<CharSequence> dateAdapter = ArrayAdapter.createFromResource(
                this, R.array.calendar_dates, android.R.layout.simple_spinner_item);
        dateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dateSpinner.setAdapter(dateAdapter);

        // Populate the time spinner with available appointment times
        ArrayAdapter<CharSequence> timeAdapter = ArrayAdapter.createFromResource(
                this, R.array.appointment_times, android.R.layout.simple_spinner_item);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeSpinner.setAdapter(timeAdapter);

        if (savedInstanceState != null) {
            dateSpinner.setSelection(savedInstanceState.getInt(DATE_SPINNER_SELECTED_POSITION));
            timeSpinner.setSelection(savedInstanceState.getInt(TIME_SPINNER_SELECTED_POSITION));
        }

        scheduleButton.setOnClickListener(view -> {
            // Get the selected date and time from the spinners
            String selectedDate = dateSpinner.getSelectedItem().toString();
            String selectedTime = timeSpinner.getSelectedItem().toString();

            Toast.makeText(ScheduleAppointmentActivity.this,
                    "Appointment scheduled for " + selectedDate + " at " + selectedTime,
                    Toast.LENGTH_SHORT).show();

            // redirect to HomeActivity again
            Intent intent = new Intent(ScheduleAppointmentActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();

        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(DATE_SPINNER_SELECTED_POSITION, dateSpinner.getSelectedItemPosition());
        outState.putInt(TIME_SPINNER_SELECTED_POSITION, timeSpinner.getSelectedItemPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            dateSpinner.setSelection(savedInstanceState.getInt(DATE_SPINNER_SELECTED_POSITION));
            timeSpinner.setSelection(savedInstanceState.getInt(TIME_SPINNER_SELECTED_POSITION));
        }
    }
}
