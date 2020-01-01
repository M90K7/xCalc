package com.mk7.xcalc.presenters.commands;

import com.mk7.xcalc.presenters.ITextHelper;

/**
 * Created by MK7 on 5/16/2015.
 */
public class MoveCursorCommand extends BaseCommand {
    @Override
    public void execute(String arg, ITextHelper textHelper) {
        if (arg.equalsIgnoreCase("next"))
            textHelper.toNextCursor();
        else if(arg.equalsIgnoreCase("previous"))
            textHelper.toPreviousCursor();

    }

    @Override
    public String getCommandName() {
        return "move";
    }

}
