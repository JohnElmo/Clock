package com.example.johnelmo.clock;

import java.util.Calendar;

public class Model {

    private int currentYear, currentMonth, currentDay, currentHour, currentMinute, currentSecond;
    private boolean changed = false;

    public Model() {
        maintainCurrentTime();
    }

    private void maintainCurrentTime() {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while(!isInterrupted()) {
                    Calendar cal = Calendar.getInstance();
                    if (changed) {
                        setCalendarInfo(cal);
                        cal.add(Calendar.SECOND, 1);
                    }
                    getCalendarInfo(cal);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        };
        t1.start();
    }

    private void getCalendarInfo(Calendar cal) {
        currentYear = cal.get(Calendar.YEAR);
        currentMonth = cal.get(Calendar.MONTH);
        currentDay = cal.get(Calendar.DAY_OF_MONTH);
        currentHour = cal.get(Calendar.HOUR_OF_DAY);
        currentMinute = cal.get(Calendar.MINUTE);
        currentSecond = cal.get(Calendar.SECOND);
    }

    private void setCalendarInfo(Calendar cal) {
        cal.set(Calendar.YEAR, currentYear);
        cal.set(Calendar.MONTH, currentMonth);
        cal.set(Calendar.DAY_OF_MONTH, currentDay);
        cal.set(Calendar.HOUR_OF_DAY, currentHour);
        cal.set(Calendar.MINUTE, currentMinute);
        cal.set(Calendar.SECOND, currentSecond);
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public int getCurrentDay() {
        return currentDay;
    }

    public int getCurrentHour() {
        return currentHour;
    }

    public int getCurrentMinute() {
        return currentMinute;
    }

    public int getCurrentSecond() {
        return currentSecond;
    }

    public void setCurrentYear(int year) {
        currentYear = year;
    }

    public void setCurrentMonth(int month) { currentMonth = month; }

    public void setCurrentDay(int day) {
        currentDay = day;
    }

    public void setCurrentHour(int hour) {
        currentHour = hour;
    }

    public void setCurrentMinute(int minute) { currentMinute = minute; }

    public void setCurrentSecond(int second) { currentSecond = second; }

    public void setChanged(boolean status) {
        changed = status;
    }

}

