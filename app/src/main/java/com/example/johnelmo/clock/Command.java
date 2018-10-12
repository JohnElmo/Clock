package com.example.johnelmo.clock;

public interface Command {
    void execute();
    void redo();
    void undo();
}
