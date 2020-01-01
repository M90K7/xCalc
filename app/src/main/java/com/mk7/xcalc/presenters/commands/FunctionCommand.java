package com.mk7.xcalc.presenters.commands;

import com.mk7.xcalc.presenters.ITextHelper;

/**
 * Created by MK7 on 5/15/2015.
 */
public class FunctionCommand extends BaseCommand {

    @Override
    public void execute(String arg, ITextHelper textHelper) {
        int sStart = textHelper.getSelectionStart();
        int sEnd = textHelper.getSelectionEnd();
        int selectLen = sEnd - sStart;

        if (selectLen > 0) {//todo: Check cursor position
            textHelper.replace(String.format("%s(%s)", arg, textHelper.getSelectionToString()), sStart, sEnd);
        } else {
            textHelper.append(arg + "()");
            textHelper.cursorPosition(textHelper.getSelectionStart() - 1);
        }
    }

    @Override
    public String getCommandName() {
        return "func";
    }

}
