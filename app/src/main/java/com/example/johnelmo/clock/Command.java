package com.example.johnelmo.clock;

public interface Command {
    void execute();
    void undo();
    void redo();
}
