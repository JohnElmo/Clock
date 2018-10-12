package com.example.johnelmo.clock;

public class AddDigitalClockCommand implements Command {

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }

    @Override
    public void redo() {
        execute();
    }
}
