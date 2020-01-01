package com.mk7.xcalc.presenters.commands;

import com.mk7.xcalc.presenters.ITextHelper;

/**
 * Created by MK7 on 5/27/2015.
 */
public class BaseCommand implements ICommandFactory, ICommand {
    @Override
    public void execute(String arg, ITextHelper textHelper) {
    }

    @Override
    public String getCommandName() {
        return null;
    }

    @Override
    public ICommand makeCommand() {
        return this;
    }
}
