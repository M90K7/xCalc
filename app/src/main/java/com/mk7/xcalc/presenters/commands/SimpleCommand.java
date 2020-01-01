package com.mk7.xcalc.presenters.commands;

import com.mk7.xcalc.presenters.ITextHelper;

/**
 * Created by MK7 on 5/16/2015.
 */
public class SimpleCommand extends BaseCommand {

    @Override
    public void execute(String arg, ITextHelper textHelper) {
        textHelper.toRemoveSelection();
        textHelper.append(arg);
    }

    @Override
    public String getCommandName() {
        return "num";
    }

}
