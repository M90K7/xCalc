package com.mk7.xcalc.presenters;

/**
 * Created by MK7 on 5/17/2015.
 */
public class ExDataModel {
    //private ParserExpression parserExpression;
    private final double value;
    private final String error;
    String ex;

    public ExDataModel(String ex, double value, String error) {
        //this.parserExpression = parserExpression;
        this.ex = ex;
        this.value = value;
        this.error = error;
    }

    public double getValue() {
        return value;
    }

    public String getValueToString() {
        return String.valueOf(getValue());
    }

    public String getError() {
        return error;
    }

    public boolean isError() {
        return (error != null);
    }

    public String getExpression() {
        return ex;
    }

    @Override
    public String toString() {
        if (isError()) return getError();
        return getValueToString();
    }
}
