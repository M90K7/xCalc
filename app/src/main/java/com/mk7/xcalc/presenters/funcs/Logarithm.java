package com.mk7.xcalc.presenters.funcs;

import org.apache.commons.math3.util.FastMath;
import org.nfunk.jep.ParseException;
import org.nfunk.jep.function.PostfixMathCommand;

import java.util.Stack;

/**
 * Created by MK7 on 6/1/2015.
 */
public class Logarithm extends PostfixMathCommand {

    public Logarithm() {
        numberOfParameters = 2;
    }

    @Override
    public void run(Stack stack) throws ParseException {

        try {
            checkStack(stack);

            Object o_num = stack.pop();
            Object o_baseNum = stack.pop();

            if (o_num instanceof Double && o_baseNum instanceof Double) {
                double num = ((Double) o_num).doubleValue();
                double baseNum = ((Double) o_baseNum).doubleValue();

                stack.push(new Double(FastMath.log(baseNum, num)));
            } else {
                throw new ParseException("Invalid parameter type");
            }
        } catch (ParseException e) {
            throw e;
        } catch (Throwable e) {
            throw new ParseException("Invalid operation exception");
        }
    }
}
