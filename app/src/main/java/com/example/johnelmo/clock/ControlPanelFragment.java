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

    private UndoRedoManager undoRedoManager = new UndoRedoManager();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.control_panel_fragment, container, false);

        Button pickDate = rootView.findViewById(R.id.dateButton);
        Button pickTime = rootView.findViewById(R.id.timeButton);
        Button digitalButton = rootView.findViewById(R.id.digitalButton);
        Button analogButton = rootView.findViewById(R.id.analogButton);
        Button redoButton = rootView.findViewById(R.id.redoButton);
        Button undoButton = rootView.findViewById(R.id.undoButton);

        pickDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), ControlPanelFragment.this,
                        MainActivity.getModel().getCurrentYear(), MainActivity.getModel().getCurrentMonth(), MainActivity.getModel().getCurrentDay());
                datePickerDialog.show();
            }
        });

        pickTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), ControlPanelFragment.this,
                        MainActivity.getModel().getCurrentHour(), MainActivity.getModel().getCurrentMinute(), DateFormat.is24HourFormat(getContext()));
                timePickerDialog.show();
            }
        });

        digitalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getSectionsPagerAdapter().addFragment(new DigitalClockFragment(), "Digital Clock");
                MainActivity.updatePageAdapter();
            }
        });

        analogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getSectionsPagerAdapter().addFragment(new AnalogClockFragment(), "Analog Clock");
                MainActivity.updatePageAdapter();
            }
        });

        redoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undoRedoManager.redo();
            }
        });

        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                undoRedoManager.undo();
            }
        });

        return rootView;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        MainActivity.getModel().setCurrentYear(year);
        MainActivity.getModel().setCurrentMonth(month);
        MainActivity.getModel().setCurrentDay(dayOfMonth);
        MainActivity.getModel().setChanged(true);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        undoRedoManager.execute(new ChangeTimeCommand(MainActivity.getModel().getCurrentHour(), MainActivity.getModel().getCurrentMinute(),
                MainActivity.getModel().getCurrentSecond(), hourOfDay, minute, 0));
    }

}