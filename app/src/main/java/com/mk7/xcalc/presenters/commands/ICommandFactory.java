package com.mk7.xcalc.presenters.commands;

/**
 * Created by MK7 on 5/15/2015.
 */
public interface ICommandFactory {
    String getCommandName();
    ICommand makeCommand();
}
