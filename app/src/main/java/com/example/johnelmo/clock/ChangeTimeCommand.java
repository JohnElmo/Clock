package com.example.johnelmo.clock;

public class ChangeTimeCommand implements Command {
    private int oldHour, oldMinute, oldSecond, newHour, newMinute, newSecond;

    public ChangeTimeCommand(int oldHour, int oldMinute, int oldSecond, int newHour, int newMinute, int newSecond) {
        this.oldHour = oldHour;
        this.oldMinute = oldMinute;
        this.oldSecond = oldSecond;
        this.newHour = newHour;
        this.newMinute = newMinute;
        this.newSecond = newSecond;
    }

    @Override
    public void execute() {
        MainActivity.getModel().setCurrentHour(newHour);
        MainActivity.getModel().setCurrentMinute(newMinute);
        MainActivity.getModel().setCurrentSecond(newSecond);
        AnalogClockFragment.setAnalogClock(MainActivity.getModel().getCurrentHour(),
                MainActivity.getModel().getCurrentMinute(), MainActivity.getModel().getCurrentSecond());
    }

    @Override
    public void redo() {
        execute();
    }

    @Override
    public void undo() {
        MainActivity.getModel().setCurrentHour(oldHour);
        MainActivity.getModel().setCurrentMinute(oldMinute);
        MainActivity.getModel().setCurrentSecond(oldSecond);
        AnalogClockFragment.setAnalogClock(MainActivity.getModel().getCurrentHour(),
                MainActivity.getModel().getCurrentMinute(), MainActivity.getModel().getCurrentSecond());
    }

}
