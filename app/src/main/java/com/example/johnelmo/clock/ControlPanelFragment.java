package com.example.johnelmo.clock;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class ControlPanelFragment extends Fragment implements DatePickerDialog.OnDateSetListener,
        TimePickerDialog.OnTimeSetListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.control_panel_fragment, container, false);

        Button pickDate = rootView.findViewById(R.id.dateButton);
        Button pickTime = rootView.findViewById(R.id.timeButton);
        Button digitalButton = rootView.findViewById(R.id.digitalButton);
        Button analogButton = rootView.findViewById(R.id.analogButton);

        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        ControlPanelFragment.this, Model.getCurrentYear(), Model.getCurrentMonth(), Model.getCurrentDay());
                datePickerDialog.show();
            }
        });

        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        ControlPanelFragment.this, Model.getCurrentHour(), Model.getCurrentMinute(), DateFormat.is24HourFormat(getContext()));
                timePickerDialog.show();
            }
        });

        digitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.updatePageAdapter(new DigitalClockFragment(), "Digital Clock");
            }
        });

        analogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.updatePageAdapter(new AnalogClockFragment(), "Analog Clock");
            }
        });

        return rootView;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Model.setCurrentYear(year);
        Model.setCurrentMonth(month);
        Model.setCurrentDay(dayOfMonth);
        Model.setChanged(true);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Model.setCurrentHour(hourOfDay);
        Model.setCurrentMinute(minute);
        Model.setCurrentSecond(0);
        Model.setChanged(true);
        AnalogClockFragment.setAnalogClock(Model.getCurrentHour(), Model.getCurrentMinute(), Model.getCurrentSecond());
    }

}