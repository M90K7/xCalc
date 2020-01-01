package com.mk7.xcalc.presenters;

import android.util.Log;

import com.mk7.xcalc.presenters.commands.ClearCommand;
import com.mk7.xcalc.presenters.commands.CommandParser;
import com.mk7.xcalc.presenters.commands.ConstCommand;
import com.mk7.xcalc.presenters.commands.FunctionCommand;
import com.mk7.xcalc.presenters.commands.ICommand;
import com.mk7.xcalc.presenters.commands.ICommandFactory;
import com.mk7.xcalc.presenters.commands.MoveCursorCommand;
import com.mk7.xcalc.presenters.commands.SimpleCommand;

import java.util.ArrayList;

/**
 * Created by MK7 on 5/17/2015.
 */
public class CalcActivityPresenter {

    final String TAG = "CalcActivityPresenter";
    ParserExpression parser;
    ICalcActivityPresenter view;
    CommandParser commandParser;

    public CalcActivityPresenter(ICalcActivityPresenter view) {
        this.view = view;
    }

    public void parse(String expression) {
        ExDataModel exData = getParser().parse(expression);
        view.setExpressionValue(exData);
        Log.d(TAG, String.format("Parse [ %s ] Expression Result: %s", expression, exData));
    }

    public void execCommand(String commandName, String arg) {
        if (commandParser == null) {
            ArrayList<ICommandFactory> commands = new ArrayList<>();
            commands.add(new SimpleCommand());
            commands.add(new FunctionCommand());
            commands.add(new MoveCursorCommand());
            commands.add(new ClearCommand());
            commands.add(new ConstCommand());
            commandParser = new CommandParser(commands);
        }

        ICommand command = commandParser.parse(commandName);
        command.execute(arg, view.getTextHelper());
    }

    private ParserExpression getParser() {
        if (parser == null)
            parser = new ParserExpression();
        return parser;
    }
}
