package com.mk7.xcalc.presenters.commands;

import com.mk7.xcalc.presenters.ITextHelper;

/**
 * Created by MK7 on 5/15/2015.
 */
public interface ICommand {
    void execute(String arg, ITextHelper textHelper);
}
