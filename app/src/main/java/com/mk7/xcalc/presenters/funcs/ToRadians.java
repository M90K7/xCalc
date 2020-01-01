package com.mk7.xcalc.presenters.funcs;

import android.text.style.TtsSpan;

import org.apache.commons.math3.util.Decimal64;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import java.math.MathContext;
import java.util.Stack;

/**
 * Created by MK7 on 6/4/2015.
 */
public class ToRadians extends PostfixMathCommand {

    public ToRadians(){
        numberOfParameters = 1;
    }

    @Override
    public void run(Stack stack) throws ParseException {
        try {
            checkStack(stack);
            Object o_num = stack.pop();
            if (o_num instanceof Double) {
                double num = ((Double) o_num).doubleValue();

                stack.push(new Double(FastMath.toRadians(num)));
            } else {
                throw new ParseException("Invalid parameter type");
            }
        } catch (ParseException e) {
            throw e;
        } catch (Throwable e) {
            throw new ParseException(e.getMessage());
        }
    }
}
