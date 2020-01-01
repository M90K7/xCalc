package com.mk7.xcalc.presenters.commands;

import com.mk7.xcalc.presenters.ITextHelper;

/**
 * Created by MK7 on 5/16/2015.
 */
public class ClearCommand extends BaseCommand {
    @Override
    public void execute(String arg, ITextHelper textHelper) {
        if(arg.equalsIgnoreCase("back"))
            textHelper.toBackspace();
        else if(arg.equalsIgnoreCase("all"))
            textHelper.clear();
    }

    @Override
    public String getCommandName() {
        return "clear";
    }

}
