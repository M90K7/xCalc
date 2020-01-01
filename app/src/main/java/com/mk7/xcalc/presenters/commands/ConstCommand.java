package com.mk7.xcalc.presenters.commands;

import com.mk7.xcalc.presenters.ITextHelper;

/**
 * Created by MK7 on 5/27/2015.
 */
public class ConstCommand extends BaseCommand {

    @Override
    public void execute(String arg, ITextHelper textHelper) {
        textHelper.toRemoveSelection();
        String text = arg;
        if(textHelper.getPreviousChar().equalsIgnoreCase(arg))
            text = "*" + text;
        if(textHelper.getNextChar().equalsIgnoreCase(arg))
            text = text + "*";
        textHelper.append(text);
    }

    @Override
    public String getCommandName() {
        return "const";
    }
}
