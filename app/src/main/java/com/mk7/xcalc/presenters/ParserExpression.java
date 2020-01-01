package com.mk7.xcalc.presenters;

import com.mk7.xcalc.presenters.funcs.CRoot;
import com.mk7.xcalc.presenters.funcs.ToDegrees;
import com.mk7.xcalc.presenters.funcs.ToRadians;

import org.nfunk.jep.JEP;

/**
 * Created by MK7 on 4/30/2015.
 */
public class ParserExpression {

    final String TAG = "ParserExpression";

    private JEP jep;

    public ParserExpression() {

        jep = new JEP();
        jep.setImplicitMul(true);
        jep.addStandardConstants();
        jep.addStandardFunctions();
        //jep.addFunction("sqr",new Sqr());
        jep.addFunction("cbrt", new CRoot());
        //jep.addFunction("log",new Logarithm());
        //jep.addFunction("nlog",new LogarithmN());
        jep.addFunction("DEG", new ToDegrees());
        jep.addFunction("RAD", new ToRadians());
        //jep.addComplex();

//        Log.d(TAG, "Java Math Result : " + String.valueOf(2D * Math.cos(1)));
//        Log.d(TAG, "Apache Math Result : " + String.valueOf(2D * FastMath.cos(1)));
    }

    public ExDataModel parse(String exp) {


        String __exp = new String(exp);
        //Replace constant value π,e
        __exp = exp.replace("π", " pi ");
        __exp = __exp.replace("e", " e ");


        jep.parseExpression(__exp);

        if (jep.hasError()) return new ExDataModel(exp, -1, jep.getErrorInfo());
            //NaN
        else if (Double.isNaN(jep.getValue())) return new ExDataModel(exp, -1, "Error-NaN");
            //∞
        else if (Double.isInfinite(jep.getValue())) return new ExDataModel(exp, -1, "∞");
            //x < 1E-15
        else if (jep.getValue() < 1E-15) return new ExDataModel(exp, 0, null);
        return new ExDataModel(exp, jep.getValue(), null);
    }

}
