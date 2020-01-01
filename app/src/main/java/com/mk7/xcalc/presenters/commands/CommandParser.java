package com.mk7.xcalc.presenters.commands;

import java.util.ArrayList;

/**
 * Created by MK7 on 5/15/2015.
 */
public class CommandParser {

    final String TAG = "CommandParser";
    ArrayList<ICommandFactory> commands;

    public CommandParser(ArrayList<ICommandFactory> commands) {
        this.commands = commands;
    }

    public ICommand parse(String commandName) {
        ICommandFactory commandFactory = findCommand(commandName);
        if (commandFactory == null) {
            return new NotFoundCommand();
        }
        return commandFactory.makeCommand();
    }

    ICommandFactory findCommand(String commandName) {
        for (ICommandFactory c : this.commands) {
            if (c.getCommandName().compareToIgnoreCase(commandName) == 0) return c;
        }
        return null;
    }

}
