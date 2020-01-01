package com.mk7.xcalc.presenters.commands;

import android.util.Log;

import com.mk7.xcalc.presenters.ITextHelper;

/**
 * Created by MK7 on 5/16/2015.
 */
public class NotFoundCommand implements ICommand {
    final String TAG = "NotFoundCommand";

    @Override
    public void execute(String arg, ITextHelper textHelper) {
        Log.e(TAG, String.format(">>> Command not found ->arg: %s", arg));
    }
}
