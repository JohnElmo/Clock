package com.example.johnelmo.clock;

import java.util.Stack;

public class UndoRedoManager {

    private Stack<Command> redoStack = new Stack<Command>();
    private Stack<Command> undoStack = new Stack<Command>();

    public void execute(Command command) {
        command.execute();
        undoStack.push(command);
        Model.setChanged(true); // Time has been changed, model is not using current time
    }

    public void redo() {
        if(!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.redo();
            undoStack.push(command);
            Model.setChanged(true); // Time has been changed, model is not using current time
        }
    }

    public void undo() {
        if(!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            if (undoStack.isEmpty()) {
                Model.setChanged(false); // All changes have been undone so model needs to reset to current time
            }
            redoStack.push(command);
        }
    }

    public boolean isUndoAvailable() {
        return !undoStack.isEmpty();
    }

}
