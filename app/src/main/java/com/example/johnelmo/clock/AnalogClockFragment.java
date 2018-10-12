package com.example.johnelmo.clock;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

public class AnalogClockFragment extends Fragment {

    private static MyVectorClock vectorAnalogClock;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.analog_clock_fragment, container, false);

        final TextView dateView = rootView.findViewById(R.id.dateTextView);
        final String format = "%02d";

        vectorAnalogClock = rootView.findViewById(R.id.analogClock);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, Model.getCurrentHour());
        cal.set(Calendar.MINUTE, Model.getCurrentMinute());
        cal.set(Calendar.SECOND, Model.getCurrentSecond());
        vectorAnalogClock.setCalendar(cal);
        vectorAnalogClock.setDiameterInDp(200.0f);
        vectorAnalogClock.setOpacity(1.0f);
        vectorAnalogClock.setShowSeconds(true);
        vectorAnalogClock.setColor(Color.BLACK);

        Thread t1 = new Thread() {
            @Override
            public void run() {
                while(!interrupted()) {
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

    private void displayDateView(TextView dateView, String format) {
        dateView.setText(String.format(format, Model.getCurrentMonth() + 1) + "/"
                + String.format(format, Model.getCurrentDay())
                + "/" + String.format(format, Model.getCurrentYear()));
    }

    public static void setAnalogClock(int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        vectorAnalogClock.setCalendar(cal);
    }
}
