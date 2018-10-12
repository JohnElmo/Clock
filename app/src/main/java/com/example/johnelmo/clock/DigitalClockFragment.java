package com.example.johnelmo.clock;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DigitalClockFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.digital_clock_fragment, container, false);

        final TextView timeView = rootView.findViewById(R.id.digitalClockTextView);
        final TextView dateView = rootView.findViewById(R.id.dateTextView);
        final String format = "%02d";

        Thread t1 = new Thread() {
            @Override
            public void run() {
                while(!interrupted()) {
                    displayTimeView(timeView, format);
                    displayDateView(dateView, format);

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

    private void displayTimeView(TextView timeView, String format) {
        timeView.setText(String.format(format, MainActivity.getModel().getCurrentHour()) + ":"
                + String.format(format, MainActivity.getModel().getCurrentMinute()) + ":"
                + String.format(format, MainActivity.getModel().getCurrentSecond()));
    }

    private void displayDateView(TextView dateView, String format) {
        dateView.setText(String.format(format, MainActivity.getModel().getCurrentMonth() + 1) + "/"
                + String.format(format, MainActivity.getModel().getCurrentDay())
                + "/" + String.format(format, MainActivity.getModel().getCurrentYear()));
    }
}
