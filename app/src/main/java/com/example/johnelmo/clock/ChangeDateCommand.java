package com.example.johnelmo.clock;

public class ChangeDateCommand implements Command {
    private int oldYear, oldMonth, oldDay, newYear, newMonth, newDay;

    public ChangeDateCommand(int oldYear, int oldMonth, int oldDay, int newYear, int newMonth, int newDay) {
        this.oldYear = oldYear;
        this.oldMonth = oldMonth;
        this.oldDay = oldDay;
        this.newYear = newYear;
        this.newMonth = newMonth;
        this.newDay = newDay;
    }

    @Override
    public void execute() {
        MainActivity.getModel().setCurrentYear(newYear);
        MainActivity.getModel().setCurrentMonth(newMonth);
        MainActivity.getModel().setCurrentDay(newDay);
    }

    @Override
    public void redo() {
        execute();
    }

    @Override
    public void undo() {
        MainActivity.getModel().setCurrentYear(oldYear);
        MainActivity.getModel().setCurrentMonth(oldMonth);
        MainActivity.getModel().setCurrentDay(oldDay);
    }
}
