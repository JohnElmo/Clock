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
        Model.setCurrentHour(newHour);
        Model.setCurrentMinute(newMinute);
        Model.setCurrentSecond(newSecond);
        AnalogClockFragment.setAnalogClock(Model.getCurrentHour(), Model.getCurrentMinute(), Model.getCurrentSecond());
    }

    @Override
    public void redo() {
        execute();
    }

    @Override
    public void undo() {
        Model.setCurrentHour(oldHour);
        Model.setCurrentMinute(oldMinute);
        Model.setCurrentSecond(oldSecond);
        AnalogClockFragment.setAnalogClock(Model.getCurrentHour(), Model.getCurrentMinute(), Model.getCurrentSecond());
    }

}
