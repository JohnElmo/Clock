package com.example.johnelmo.clock;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DigitalClockFragment extends Fragment {

    TextView timeView, dateView;
    String format = "%02d";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.digital_clock_fragment, container, false);

        timeView = rootView.findViewById(R.id.digitalClockTextView);
        dateView = rootView.findViewById(R.id.dateTextView);

        Thread t1 = new Thread() {
            @Override
            public void run() {
                while(!interrupted()) {
                    displayViews();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();

        return rootView;
    }

    public void displayViews() {
        timeView.setText(String.format(format, Model.getCurrentHour()) + ":"
                + String.format(format, Model.getCurrentMinute()) + ":"
                + String.format(format, Model.getCurrentSecond()));

        dateView.setText(String.format(format, Model.getCurrentMonth() + 1) + "/"
                + String.format(format, Model.getCurrentDay())
                + "/" + String.format(format, Model.getCurrentYear()));
    }
}
